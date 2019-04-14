package com.jmsilla.crmapitest.application.dtos.user;

import com.jmsilla.crmapitest.application.dtos.BaseResponse;

public class UpdateUserResponse extends BaseResponse {
	private Integer updatedUserId;

	public Integer getUpdatedUserId() {
		return updatedUserId;
	}

	public void setUpdatedUserId(Integer updatedUserId) {
		this.updatedUserId = updatedUserId;
	}
}
