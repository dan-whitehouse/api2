package org.ricone.api.oneroster.component.error.exception;

public class BlankFieldSelectionException extends Exception {
	private static final long serialVersionUID = 1997753363232807009L;

	public BlankFieldSelectionException() {
		super();
	}

	public BlankFieldSelectionException(String message) {
		super(message);
	}

	public BlankFieldSelectionException(Throwable cause) {
		super(cause);
	}

	public BlankFieldSelectionException(String message, Throwable cause) {
		super(message, cause);
	}

	public BlankFieldSelectionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
