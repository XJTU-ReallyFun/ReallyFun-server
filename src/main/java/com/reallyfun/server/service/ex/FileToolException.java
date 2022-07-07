package com.reallyfun.server.service.ex;

public class FileToolException extends ServiceException {
    public FileToolException() {
    }

    public FileToolException(String message) {
        super(message);
    }

    public FileToolException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileToolException(Throwable cause) {
        super(cause);
    }

    public FileToolException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
