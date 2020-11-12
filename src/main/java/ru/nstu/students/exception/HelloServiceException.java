package ru.nstu.students.exception;

public class HelloServiceException extends RuntimeException {

    private static final long serialVersionUID = 7776734367104079995L;

    public HelloServiceException() {
        super();
    }

    public HelloServiceException(String message) {
        super(message);
    }

    public HelloServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public HelloServiceException(Throwable cause) {
        super(cause);
    }

    protected HelloServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
