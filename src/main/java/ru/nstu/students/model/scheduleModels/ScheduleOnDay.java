package ru.nstu.students.model.scheduleModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
public class ScheduleOnDay {

    String day;

    ArrayList<Lesson> lessons;

    public ScheduleOnDay(String day) {
        this.day = day;
        lessons = new ArrayList<>();
    }

    public void addLesson(Lesson lesson) {
        if (this.lessons == null) {
            lessons = new ArrayList<>();
        }
        lessons.add(lesson);
    }
}
