package jweblite.web;

public class SkipException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public SkipException() {
		super();
	}

	/**
	 * Default constructor.
	 */
	public SkipException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Default constructor.
	 */
	public SkipException(String message) {
		super(message);
	}

	/**
	 * Default constructor.
	 */
	public SkipException(Throwable cause) {
		super(cause);
	}

}