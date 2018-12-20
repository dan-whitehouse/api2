package org.ricone.api.xpress.error.exception;

public class MappingException extends Exception {
    private static final long serialVersionUID = 1417351363726882509L;

    public MappingException() {
        super();
    }

    public MappingException(String message) {
        super(message);
    }

    public MappingException(Throwable cause) {
        super(cause);
    }

    public MappingException(String message, Throwable cause) {
        super(message, cause);
    }

    public MappingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}