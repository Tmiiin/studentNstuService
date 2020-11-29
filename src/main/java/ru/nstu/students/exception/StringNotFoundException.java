package ru.nstu.students.exception;

public class StringNotFoundException extends StudentsServiceException {

    StringNotFoundException(String string){
        super("Group with number " + string + " not found");
    }
}
