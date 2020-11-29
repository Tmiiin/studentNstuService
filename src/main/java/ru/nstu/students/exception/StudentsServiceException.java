package ru.nstu.students.exception;

public class StudentsServiceException extends RuntimeException {

    private static final long serialVersionUID = 7776734367104079995L;

    public StudentsServiceException() {
        super();
    }

    public StudentsServiceException(String message) {
        super(message);
    }

    public StudentsServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public StudentsServiceException(Throwable cause) {
        super(cause);
    }

    protected StudentsServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
