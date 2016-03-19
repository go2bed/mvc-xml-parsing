package com.epam.chadov.task3.xml.utils.exceptions;

public class DateConverterException extends RuntimeException{
    public DateConverterException(String message) {
        super(message);
    }

    public DateConverterException(String message, Throwable cause) {
        super(message, cause);
    }

    public DateConverterException(Throwable cause) {
        super(cause);
    }

    protected DateConverterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
