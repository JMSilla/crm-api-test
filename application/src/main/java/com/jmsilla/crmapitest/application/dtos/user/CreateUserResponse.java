package com.jmsilla.crmapitest.application.dtos.user;

import com.jmsilla.crmapitest.application.dtos.BaseResponse;

public class CreateUserResponse extends BaseResponse {
	private Integer createdUserId;

	public Integer getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(Integer createdUserId) {
		this.createdUserId = createdUserId;
	}
}
