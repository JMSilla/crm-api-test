package com.jmsilla.crmapitest.application.dtos;

public class GetUserRequest {
	private String requestingUser;
	private Integer userId;

	public String getRequestingUser() {
		return requestingUser;
	}

	public void setRequestingUser(String requestingUser) {
		this.requestingUser = requestingUser;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
