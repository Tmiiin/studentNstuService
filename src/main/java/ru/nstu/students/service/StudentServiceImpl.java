package ru.nstu.students.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.nstu.students.exception.StudentsServiceException;
import ru.nstu.students.repository.StudentRepository;
import ru.nstu.students.model.StudentEntity;

import java.util.List;
import java.util.UUID;

@Component
public class StudentServiceImpl implements StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    private final StudentRepository repository;

    public StudentServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public StudentEntity getByUUid(UUID uuid) {
        return repository.getOne(uuid);
    }

    @Override
    public StudentEntity createStringEntity(String string) throws StudentsServiceException {
        StudentEntity entity = new StudentEntity(string);
        logger.info("Entity created: " + entity);
        return repository.save(entity);
    }

    @Override
    public void deleteStringEntity(UUID uuid) throws StudentsServiceException {

    }

    @Override
    public List<StudentEntity> findAll() {
        return repository.findAll();
    }

}
