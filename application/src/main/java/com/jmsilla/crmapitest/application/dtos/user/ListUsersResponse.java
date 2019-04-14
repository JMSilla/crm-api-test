package com.jmsilla.crmapitest.application.dtos.user;

import java.util.List;

import com.jmsilla.crmapitest.application.dtos.BaseResponse;

public class ListUsersResponse extends BaseResponse {
	private List<GetUserResponse> users;

	public List<GetUserResponse> getUsers() {
		return users;
	}

	public void setUsers(List<GetUserResponse> users) {
		this.users = users;
	}
}
