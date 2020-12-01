package ru.nstu.students.service;

import org.springframework.transaction.annotation.Transactional;
import ru.nstu.students.exception.NoteNotFoundException;
import ru.nstu.students.exception.StudentsServiceException;
import ru.nstu.students.model.NoteEntity;

import java.util.List;
import java.util.UUID;

@Transactional(readOnly = true)
public interface NoteService {

    List<NoteEntity> findAll();

    NoteEntity getByUUid(UUID uuid) throws NoteNotFoundException;

    @Transactional
    NoteEntity createNote(String string) throws NoteNotFoundException;

    @Transactional
    void deleteNote(UUID uuid) throws NoteNotFoundException;

}
