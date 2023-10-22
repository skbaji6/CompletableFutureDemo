package com.samay.CFDemo.exception;

// FWCException is a custom exception class that extends the standard Java Exception class.
public class ServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	// A constructor that takes only a custom message as an argument.
	public ServiceException(String message) {
		super(message);
	}

	// A constructor that takes both a custom message and a cause as arguments.
	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	// A constructor that takes the cause of the exception as an argument.
	public ServiceException(Throwable cause) {
		super(cause);
	}

	// A constructor with no arguments.
	public ServiceException() {
		super();
	}
}
