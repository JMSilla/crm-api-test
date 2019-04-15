package com.jmsilla.crmapitest.application.dtos.customer;

import com.jmsilla.crmapitest.application.dtos.BaseResponse;

public class GetCustomerDetailsResponse extends BaseResponse {
	private Integer id;
	private String name;
	private String surname;
	private String createdByUsername;
	private String modifiedByUsername;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getCreatedByUsername() {
		return createdByUsername;
	}

	public void setCreatedByUsername(String createdByUsername) {
		this.createdByUsername = createdByUsername;
	}

	public String getModifiedByUsername() {
		return modifiedByUsername;
	}

	public void setModifiedByUsername(String modifiedByUsername) {
		this.modifiedByUsername = modifiedByUsername;
	}
}
