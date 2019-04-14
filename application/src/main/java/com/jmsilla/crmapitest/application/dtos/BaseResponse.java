package com.jmsilla.crmapitest.application.dtos;

public class BaseResponse {
	private Boolean error = false;
	private String errorMessage;
	
	public Boolean hasError() {
		return error;
	}

	public void setError(Boolean error) {
		this.error = error;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
