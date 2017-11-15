package com.celmam.exception;

public class CMAdapterServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	private String errorCode="Error desconocido";

	public CMAdapterServiceException() {
		super();
	}

	public CMAdapterServiceException(String message) {
		super(message);
		}

	public CMAdapterServiceException(String message, String errorCode){
		super(message);
		this.errorCode=errorCode;
	}

	public CMAdapterServiceException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public CMAdapterServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
	public CMAdapterServiceException(Throwable cause) {
		super(cause);
	}
	
	public String getErrorCode(){
		return this.errorCode;
	}
	

}