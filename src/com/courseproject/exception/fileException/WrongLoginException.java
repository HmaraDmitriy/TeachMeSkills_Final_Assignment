package com.courseproject.exception.fileException;

public class WrongLoginException extends RuntimeException {
    public WrongLoginException(String message, Throwable cause) {
        super(message, cause);
    }
}
