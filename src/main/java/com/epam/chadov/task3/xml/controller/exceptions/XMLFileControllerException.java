package com.epam.chadov.task3.xml.controller.exceptions;


public class XMLFileControllerException extends RuntimeException{
    public XMLFileControllerException(String message) {
        super(message);
    }

    public XMLFileControllerException(String message, Throwable cause) {
        super(message, cause);
    }

    public XMLFileControllerException(Throwable cause) {
        super(cause);
    }

    protected XMLFileControllerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
