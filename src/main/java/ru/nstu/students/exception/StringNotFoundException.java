package ru.nstu.students.exception;

public class StringNotFoundException extends StudentsServiceException {

    public StringNotFoundException(String string){
        super(string);
    }
}
