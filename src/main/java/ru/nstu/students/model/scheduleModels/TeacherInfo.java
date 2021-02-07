package ru.nstu.students.model.scheduleModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class TeacherInfo {

    String teacherUrl = "";
    String teacherName = "";

    private TeacherInfo() {
    }

    public static Builder builder() {
        return new TeacherInfo().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder setTeacherUrl(String teacherUrl) {
            TeacherInfo.this.teacherUrl = teacherUrl;
            return this;
        }

        public Builder setTeacherName(String teacherName) {
            TeacherInfo.this.teacherName = teacherName;
            return this;
        }

        public TeacherInfo build() {
            return TeacherInfo.this;
        }
    }
}
