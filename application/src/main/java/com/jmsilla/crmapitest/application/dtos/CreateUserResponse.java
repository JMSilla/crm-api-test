package com.jmsilla.crmapitest.application.dtos;

public class CreateUserResponse extends BaseResponse {
	private Integer createdUserId;

	public Integer getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(Integer createdUserId) {
		this.createdUserId = createdUserId;
	}
}
