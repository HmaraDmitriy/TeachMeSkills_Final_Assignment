package com.courseproject.exception.fileException;

public class NullFolderException extends Exception{
    private int exceptionCode;

    public NullFolderException(String message, int exceptionCode) {
        super(message);
        this.exceptionCode = exceptionCode;
    }
}