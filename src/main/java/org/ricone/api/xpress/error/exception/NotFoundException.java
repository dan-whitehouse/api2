package org.ricone.api.xpress.error.exception;

public class NotFoundException extends Exception {
    private static final long serialVersionUID = 1997753363232807009L;

    public NotFoundException() {
        super("Data doesn't appear to exist, or it can't be found...");
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(Throwable cause) {
        super(cause);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}