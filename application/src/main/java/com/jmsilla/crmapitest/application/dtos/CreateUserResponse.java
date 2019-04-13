package com.jmsilla.crmapitest.application.dtos;

public class CreateUserResponse {
	private Boolean error = false;
	private String errorMessage; 
	private Integer createdUserId;

	public Boolean getError() {
		return error;
	}

	public void setError(Boolean error) {
		this.error = error;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorCode(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Integer getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(Integer createdUserId) {
		this.createdUserId = createdUserId;
	}
}
