package com.reallyfun.server.service.ex;

public class ContentDuplicateException extends ServiceException{
    public ContentDuplicateException() {
        super();
    }

    public ContentDuplicateException(String message) {
        super(message);
    }

    public ContentDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public ContentDuplicateException(Throwable cause) {
        super(cause);
    }

    protected ContentDuplicateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
