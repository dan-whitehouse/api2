package org.ricone.api.oneroster.component.error.exception;

public class InvalidFilterFieldException extends Exception {
	private static final long serialVersionUID = 1997753363232807009L;

	public InvalidFilterFieldException() {
		super();
	}

	public InvalidFilterFieldException(String message) {
		super(message);
	}

	public InvalidFilterFieldException(Throwable cause) {
		super(cause);
	}

	public InvalidFilterFieldException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidFilterFieldException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
