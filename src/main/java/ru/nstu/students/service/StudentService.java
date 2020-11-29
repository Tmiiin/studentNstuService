package ru.nstu.students.service;

import org.springframework.transaction.annotation.Transactional;
import ru.nstu.students.exception.StudentsServiceException;
import ru.nstu.students.model.StudentEntity;

import java.util.List;
import java.util.UUID;

@Transactional(readOnly = true)
public interface StudentService {

    List<StudentEntity> findAll();

    StudentEntity getByUUid(UUID uuid);

    @Transactional
    StudentEntity createStringEntity(String string) throws StudentsServiceException;

    @Transactional
    void deleteStringEntity(UUID uuid) throws StudentsServiceException;

}
