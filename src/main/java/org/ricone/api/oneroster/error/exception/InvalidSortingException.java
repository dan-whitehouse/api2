package org.ricone.api.oneroster.error.exception;

public class InvalidSortingException extends Exception {
	private static final long serialVersionUID = 1997753363232807009L;

	public InvalidSortingException() {
		super();
	}

	public InvalidSortingException(String message) {
		super(message);
	}

	public InvalidSortingException(Throwable cause) {
		super(cause);
	}

	public InvalidSortingException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidSortingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
