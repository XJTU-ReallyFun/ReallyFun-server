package com.reallyfun.server.service.ex;

public class RatingException extends ServiceException {
    public RatingException() {
        super();
    }

    public RatingException(String message) {
        super(message);
    }

    public RatingException(String message, Throwable cause) {
        super(message, cause);
    }

    public RatingException(Throwable cause) {
        super(cause);
    }

    protected RatingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
