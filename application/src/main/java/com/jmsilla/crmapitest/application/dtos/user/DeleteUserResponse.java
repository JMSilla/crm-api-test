package com.jmsilla.crmapitest.application.dtos.user;

import com.jmsilla.crmapitest.application.dtos.BaseResponse;

public class DeleteUserResponse extends BaseResponse {
	private Integer deletedUserId;

	public Integer getDeletedUserId() {
		return deletedUserId;
	}

	public void setDeletedUserId(Integer deletedUserId) {
		this.deletedUserId = deletedUserId;
	}
}
