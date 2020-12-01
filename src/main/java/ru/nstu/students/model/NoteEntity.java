package ru.nstu.students.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import ru.nstu.students.model.enums.NoteGroup;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
public class NoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id; //PK

    @Column
    private UUID authorId; //ссылка на автора

    @Column
    private String authorName; //содержит имя+фамилию+отчество студента оформившего заметку

    @Lob //make column with type TEXT
    private String noteText; //текст заметки

    @Column
    private LocalDateTime date; //дата создания заметки

    @Column(length = 10)
    private NoteGroup noteGroup; //как область видимости заметки

    public static Builder Builder() {
        return new NoteEntity().new Builder();
    }

    public class Builder{

        private Builder(){
            NoteEntity.this.date = LocalDateTime.now();
        }

        public Builder setNoteGroup(NoteGroup noteGroup){
            NoteEntity.this.noteGroup = noteGroup;
            return this;
        }

        public Builder setNoteText(String noteText){
            NoteEntity.this.noteText = noteText;
            return this;
        }

        public Builder setAuthorName(String authorName){
            NoteEntity.this.authorName = authorName;
            return this;
        }

        public Builder setAuthorId(UUID id){
            NoteEntity.this.authorId = id;
            return this;
        }

        public NoteEntity build(){
            return NoteEntity.this;
        }
    }
}
