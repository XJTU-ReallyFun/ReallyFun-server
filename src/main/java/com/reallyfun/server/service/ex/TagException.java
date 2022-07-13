package com.reallyfun.server.service.ex;

public class TagException extends ServiceException {
    public TagException() {
    }

    public TagException(String message) {
        super(message);
    }

    public TagException(String message, Throwable cause) {
        super(message, cause);
    }

    public TagException(Throwable cause) {
        super(cause);
    }

    public TagException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
