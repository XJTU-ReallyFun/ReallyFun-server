package com.reallyfun.server.service.ex;

public class FavoriteException extends ServiceException {
    public FavoriteException() {
        super();
    }

    public FavoriteException(String message) {
        super(message);
    }

    public FavoriteException(String message, Throwable cause) {
        super(message, cause);
    }

    public FavoriteException(Throwable cause) {
        super(cause);
    }

    protected FavoriteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
