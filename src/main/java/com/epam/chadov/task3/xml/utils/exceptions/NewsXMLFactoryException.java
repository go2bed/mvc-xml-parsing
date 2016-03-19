package com.epam.chadov.task3.xml.utils.exceptions;

public class NewsXMLFactoryException extends RuntimeException{
    public NewsXMLFactoryException(String message) {
        super(message);
    }

    public NewsXMLFactoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public NewsXMLFactoryException(Throwable cause) {
        super(cause);
    }

    protected NewsXMLFactoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
