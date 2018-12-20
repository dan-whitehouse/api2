package org.ricone.api.xpress.error.exception;

public class NoContentException extends Exception {
    private static final long serialVersionUID = 4571363863275275931L;

    public NoContentException() {
        super();
    }

    public NoContentException(String message) {
        super(message);
    }

    public NoContentException(Throwable cause) {
        super(cause);
    }

    public NoContentException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoContentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}