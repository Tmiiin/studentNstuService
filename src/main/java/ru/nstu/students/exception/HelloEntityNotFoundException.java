package ru.nstu.students.exception;

import java.util.UUID;

public class HelloEntityNotFoundException extends HelloServiceException {

    private static final long serialVersionUID = 5572380128132866720L;

    public HelloEntityNotFoundException(UUID uuid) {
        super("Entity with uuid '" + uuid.toString() + "' not found.");
    }
}
