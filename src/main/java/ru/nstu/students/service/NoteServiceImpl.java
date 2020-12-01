package ru.nstu.students.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.nstu.students.exception.StudentsServiceException;
import ru.nstu.students.model.NoteEntity;
import ru.nstu.students.model.enums.NoteGroup;
import ru.nstu.students.repository.NoteRepository;
import ru.nstu.students.model.StudentEntity;

import java.util.List;
import java.util.UUID;

@Component
public class NoteServiceImpl implements NoteService {

    private static final Logger logger = LoggerFactory.getLogger(NoteServiceImpl.class);

    private final NoteRepository repository;

    public NoteServiceImpl(NoteRepository repository) {
        this.repository = repository;
    }

    @Override
    public NoteEntity getByUUid(UUID uuid) {
        return repository.getOne(uuid);
    }

    @Override
    public NoteEntity createNote(String string) throws StudentsServiceException {
        NoteEntity note = NoteEntity.Builder()
                .setNoteGroup(NoteGroup.FOR_ALL)
                .setNoteText("some text for all users!!")
                .setAuthorId(UUID.randomUUID())
                .build();
        if(note == null)
            throw new StudentsServiceException("Не удалось создать заметку...");
        logger.info("Entity created: " + note);
        return repository.save(note);
    }

    @Override
    public void deleteNote(UUID uuid) throws StudentsServiceException {

    }

    @Override
    public List<NoteEntity> findAll() {
        return repository.findAll();
    }

}
