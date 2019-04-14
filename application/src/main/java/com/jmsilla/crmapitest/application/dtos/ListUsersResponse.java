package com.jmsilla.crmapitest.application.dtos;

import java.util.List;

public class ListUsersResponse extends BaseResponse {
	private List<GetUserResponse> users;

	public List<GetUserResponse> getUsers() {
		return users;
	}

	public void setUsers(List<GetUserResponse> users) {
		this.users = users;
	}
}
