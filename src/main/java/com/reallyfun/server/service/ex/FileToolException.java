package com.reallyfun.server.service.ex;

import java.io.IOException;

public class FileToolException extends IOException {
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
}
