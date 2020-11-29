package ru.nstu.students.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
public class NoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id; //PK

    @Column
    private UUID author_id; //ссылка на автора

    @Lob //make column with type TEXT
    private String note_text; //текст заметки

    @Column
    private LocalDate date; //дата создания заметки

    @Column
    @Length(max = 10)
    private String note_group; //как область видимости заметки

}
