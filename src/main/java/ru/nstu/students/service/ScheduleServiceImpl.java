package ru.nstu.students.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.nstu.students.model.scheduleModels.Lesson;
import ru.nstu.students.model.scheduleModels.ScheduleOnDay;
import ru.nstu.students.model.scheduleModels.TeacherInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ScheduleServiceImpl {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleServiceImpl.class);

    @Value("${path.to.schedule}")
    String scheduleURL;

    String cssQuery = "div.schedule__table-body";

    public void getSchedule(String groupName) throws IOException {
        Document doc = Jsoup.connect(scheduleURL + "?group=АА-06").get();
        List<ScheduleOnDay> schedule = new ArrayList<>();
        Element listDays = doc.select(cssQuery).first();
        Elements el = listDays.children();
        ScheduleOnDay scheduleOnDay;
        for (Element day : el) {//обрабатываем пары на каждый день из html в entity
            String dayName = day.select("div.schedule__table-day").text();//получаем имя дня (пн)
            scheduleOnDay = new ScheduleOnDay(dayName);
            for (Element lessons2 : day.children().get(1).children()) {//по порядку получаем пары
                if (lessons2.children() != null) { //если пары есть
                    Lesson.Builder lessonBuilder = Lesson.builder();
                    String time = lessons2.select("div.schedule__table-time").text();
                    //рассматривает вариант четной/нечетной пары
                    for (Element lessons : lessons2.children().get(1).children()) { //get(0) - получает "пн"
                        int lessonNameLength = 0; //длина лишних слов в строке
                        int additionalLength = 0; //дополнительные пробелы
                        int parityLength = 0; //если есть (!)ЧЕТНОСТЬ то она будет впереди рушить красоту
                        String lessonName = lessons.select("div.schedule__table-item").text();

                        if (!lessonName.isEmpty()) {
                            String auditory = lessons.select("div.schedule__table-class").text();
                            String parity = lessons.select("span.schedule__table-label").text();
                            lessonNameLength += auditory.length();
                            lessonBuilder.setAuditory(auditory)
                                    .setParity(parity)
                                    .setTime(time);
                            //получаем доступные ссылки на преподавателей и их имена
                            Elements link = lessons.select("div.schedule__table-item > a");
                            if (link != null) {//если кто-то ведет урок - смотрим кто
                                for (Element teacher : link) { //преподавателей может быть несколько
                                    TeacherInfo teacherInfo = TeacherInfo.builder()
                                            .setTeacherUrl(teacher.attr("href"))
                                            .setTeacherName(teacher.text())
                                            .build();
                                    lessonBuilder.addTeacherInfo(teacherInfo);
                                    logger.info("TeacherInfo: " + teacherInfo);
                                    lessonNameLength += teacher.text().length();
                                    additionalLength++;
                                }
                            }
                            if (additionalLength > 0) additionalLength += 3; //если преподаватели были, то чтобы
                            //правильно обрезать строку, придется добавить дополнительный пробел между ними и аудиторией
                            //+ каждое название пары заканчивается точкой и пробелом перед ним -> обрезаем
                            if (!parity.isEmpty())
                                parityLength = parity.length() + 1;
                            //из-за невозможности использовать css-селекторы и нежелания парсить руками, читаем весь
                            // текст из тега div и обрезаем лишние значения
                            lessonName = lessonName.substring(parityLength, lessonName.length() - lessonNameLength
                                    - additionalLength);
                            lessonBuilder.setLessonName(lessonName);
                            scheduleOnDay.addLesson(lessonBuilder.build());
                        }
                    }
                }
            }
            schedule.add(scheduleOnDay);
        }
        logger.info("Что же мы увидим в дебагере...");
    }
}
