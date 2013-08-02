package com.ksoft.core.exception;

public class InternalException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int errorCode = 0;

	

	/**
	 * 
	 */
	public InternalException() {

	}

	/**
	 * 
	 */
	public InternalException(String strMsg) {
		super(strMsg);
	}
	/**
	 * 
	 */
	public InternalException(int errorCode, String strMsg) {
		super(strMsg);
		setErrorCode(errorCode);
	}
	
	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}
