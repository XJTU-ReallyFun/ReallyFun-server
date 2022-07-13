package com.reallyfun.server.service.ex;

public class FeedbackException extends ServiceException {
    public FeedbackException() {
    }

    public FeedbackException(String message) {
        super(message);
    }

    public FeedbackException(String message, Throwable cause) {
        super(message, cause);
    }

    public FeedbackException(Throwable cause) {
        super(cause);
    }

    public FeedbackException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
