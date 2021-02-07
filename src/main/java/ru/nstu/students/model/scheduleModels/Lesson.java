package ru.nstu.students.model.scheduleModels;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
public class Lesson {

    ArrayList<TeacherInfo> teacherInfo;
    String parity = "";
    String time = "";
    String auditory = "";
    String lessonName = "";

    private Lesson() {
        this.teacherInfo = new ArrayList<>();
    }

    public static Builder builder() {
        return new Lesson().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder addTeacherInfo(TeacherInfo teacherInfo) {
            Lesson.this.teacherInfo.add(teacherInfo);
            return this;
        }

        public Builder setParity(String parity) {
            Lesson.this.parity = parity;
            return this;
        }

        public Builder setTime(String time) {
            Lesson.this.time = time;
            return this;
        }

        public Builder setAuditory(String auditory) {
            Lesson.this.auditory = auditory;
            return this;
        }

        public Builder setLessonName(String lessonName) {
            Lesson.this.lessonName = lessonName;
            return this;
        }

        public Lesson build() {
            return Lesson.this;
        }

    }


}
