package com.cognizant.training.bloodBank.exception;

public class LocationNotFoundException extends Exception{

	private static final long serialVersionUID = 1L;

	public LocationNotFoundException() {
		super();
	}
	
	public LocationNotFoundException(String message) {
		super(message);
	}
	
	public LocationNotFoundException(Throwable cause) {
		super(cause);
	}

	public LocationNotFoundException(String message, Throwable cause) {
	       super(message, cause);
	}

	protected LocationNotFoundException (String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
	        super(message, cause, enableSuppression, writableStackTrace);
	}
}
	
