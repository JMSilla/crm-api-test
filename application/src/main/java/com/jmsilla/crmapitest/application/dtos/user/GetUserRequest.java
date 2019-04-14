package com.jmsilla.crmapitest.application.dtos.user;

import com.jmsilla.crmapitest.application.dtos.BaseRequest;

public class GetUserRequest extends BaseRequest {
	private Integer userId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
