package com.jmsilla.crmapitest.application.dtos;

public class CreateUserRequest extends BaseRequest {
	private String name;
	private Boolean isAdmin;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
}
