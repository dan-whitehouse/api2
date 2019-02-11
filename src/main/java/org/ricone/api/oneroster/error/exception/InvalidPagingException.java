package org.ricone.api.oneroster.error.exception;

public class InvalidPagingException extends Exception {
	private static final long serialVersionUID = 1997753363232807009L;

	public InvalidPagingException() {
		super();
	}

	public InvalidPagingException(String message) {
		super(message);
	}

	public InvalidPagingException(Throwable cause) {
		super(cause);
	}

	public InvalidPagingException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidPagingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
