package com.zzz.springboot.exception;

public class CustomException extends Exception {
	/** message*/
	private String message;

	/**
	 * <p>Title: </p>
	 * <p>Description: </p>
	 * @param message
	 */
	public CustomException(String message) {
		super();
		this.message = message;
	}

	/**
	 * @return message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message 要设置的 message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
