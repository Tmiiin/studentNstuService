package ru.nstu.students.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.nstu.students.exception.StringNotFoundException;
import ru.nstu.students.exception.StudentsServiceException;
import ru.nstu.students.model.scheduleModels.Lesson;
import ru.nstu.students.model.scheduleModels.ScheduleOnDay;
import ru.nstu.students.model.scheduleModels.TeacherInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ScheduleServiceImpl {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleServiceImpl.class);

    @Value("${path.to.schedule.group}")
    String scheduleGroupURL;

    @Value("${path.to.schedule}")
    String scheduleURL;
    String groupURL = "https://www.nstu.ru/studies/schedule/schedule_classes";

    String qGetSchedule = "div.schedule__table-body";
    String qGetDayOfWeek = "div.schedule__table-day";
    String qGetLessonTime = "div.schedule__table-time";
    String qGetScheduleTableItem = "div.schedule__table-item";
    String qTeacherInfo = "div.schedule__table-item > a";
    String qLink = "href";
    String qGetParity = "span.schedule__table-label";
    String qGetAuditory = "div.schedule__table-class";
    String qGetWeekNumber = "div.header__label > span";


    public String getNumberOfWeek() throws IOException {
        Document doc = Jsoup.connect(scheduleURL).get();
        Element weekNumber = doc.select(qGetWeekNumber).first();
        if(!weekNumber.text().isEmpty()) return  weekNumber.text();
        else throw new StringNotFoundException("Can't parse number of this week");
    }

    public List<ScheduleOnDay> getSchedule(String groupName) throws IOException {
        Document doc = Jsoup.connect(scheduleGroupURL + groupName).get();
        List<ScheduleOnDay> schedule = new ArrayList<>();
        Element listDays = doc.select(qGetSchedule).first();
        Elements el = listDays.children();
        ScheduleOnDay scheduleOnDay;
        for (Element day : el) {//обрабатываем пары на каждый день из html в entity
            String dayName = day.select(qGetDayOfWeek).text();//получаем имя дня (пн)
            scheduleOnDay = new ScheduleOnDay(dayName);
            for (Element lessons2 : day.children().get(1).children()) {//по порядку получаем пары
                if (lessons2.children() != null) { //если пары есть
                     //рассматривает вариант четной/нечетной пары
                    for (Element lessons : lessons2.children().get(1).children()) { //get(0) - получает "пн"
                        Lesson.Builder lessonBuilder = Lesson.builder();
                        String time = lessons2.select(qGetLessonTime).text();
                        ArrayList<String> foolStrings = new ArrayList<>();
                        String lessonName = lessons.select(qGetScheduleTableItem).text();

                        if (!lessonName.isEmpty()) {
                            String auditory = lessons.select(qGetAuditory).text();
                            String parity = lessons.select(qGetParity).text();
                            lessonBuilder.setAuditory(auditory)
                                    .setParity(parity)
                                    .setTime(time);
                            //получаем доступные ссылки на преподавателей и их имена
                            Elements link = lessons.select(qTeacherInfo);
                            if (link != null) {//если кто-то ведет урок - смотрим кто
                                for (Element teacher : link) { //преподавателей может быть несколько
                                    TeacherInfo teacherInfo = TeacherInfo.builder()
                                            .setTeacherUrl(teacher.attr(qLink))
                                            .setTeacherName(teacher.text())
                                            .build();
                                    lessonBuilder.addTeacherInfo(teacherInfo);
                                    foolStrings.add(teacher.text());
                                }
                            }
                            foolStrings.add(parity);
                            foolStrings.add(auditory);
                            lessonBuilder.setLessonName(deleteFoolString(lessonName, foolStrings));
                            scheduleOnDay.addLesson(lessonBuilder.build());
                        }
                    }
                }
            }
            schedule.add(scheduleOnDay);
        }
        logger.info(schedule.toString());
        return schedule;
    }

    private String deleteFoolString(String lessonName, ArrayList<String> foolStrings) {
        for (String foolString : foolStrings)
            lessonName = lessonName.replace(foolString, "");
        //на конце в html странная точка с пробелом
        //а между именами преподавателей ставится запятая
        lessonName = lessonName.replace(",","");
        lessonName = lessonName.replace("·","");
        return lessonName.trim();
    }

    public List<String> getGroupNames() throws IOException {
        List<String> groupNames = new ArrayList<>();
        Document doc = Jsoup.connect(groupURL).get();

        return null;
    }


}
