package com.jmsilla.crmapitest.application.dtos;

public class DeleteUserRequest extends BaseRequest {
	private Integer userId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
