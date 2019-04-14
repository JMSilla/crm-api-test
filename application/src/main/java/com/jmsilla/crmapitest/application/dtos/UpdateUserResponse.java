package com.jmsilla.crmapitest.application.dtos;

public class UpdateUserResponse extends BaseResponse {
	private Integer updatedUserId;

	public Integer getUpdatedUserId() {
		return updatedUserId;
	}

	public void setUpdatedUserId(Integer updatedUserId) {
		this.updatedUserId = updatedUserId;
	}
}
