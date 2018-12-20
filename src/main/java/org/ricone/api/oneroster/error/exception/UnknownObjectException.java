package org.ricone.api.oneroster.error.exception;

public class UnknownObjectException extends Exception {
	private static final long serialVersionUID = 1997753363232807009L;

	public UnknownObjectException() {
		super();
	}

	public UnknownObjectException(String message) {
		super(message);
	}

	public UnknownObjectException(Throwable cause) {
		super(cause);
	}

	public UnknownObjectException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnknownObjectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
