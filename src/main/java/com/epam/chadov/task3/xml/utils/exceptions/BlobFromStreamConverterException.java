package com.epam.chadov.task3.xml.utils.exceptions;

public class BlobFromStreamConverterException extends RuntimeException {
    public BlobFromStreamConverterException(String message) {
        super(message);
    }

    public BlobFromStreamConverterException(String message, Throwable cause) {
        super(message, cause);
    }

    public BlobFromStreamConverterException(Throwable cause) {
        super(cause);
    }

    public BlobFromStreamConverterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
