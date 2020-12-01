package ru.nstu.students.exception;

import java.util.UUID;

public class NoteNotFoundException extends StudentsServiceException  {

    public NoteNotFoundException(UUID id){ super("Note with id " + id + " not found");}
}
