package com.jmsilla.crmapitest.application.dtos.customer;

import com.jmsilla.crmapitest.application.dtos.BaseRequest;

public class UpdateCustomerRequest extends BaseRequest {
	private Integer id;
	private String name;
	private String surname;

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
}
