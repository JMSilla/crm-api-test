package com.jmsilla.crmapitest.application.dtos.customer;

import com.jmsilla.crmapitest.application.dtos.BaseResponse;

public class UpdateCustomerResponse extends BaseResponse {
	private Integer updatedCustomerId;
	private String name;
	private String surname;

	public Integer getUpdatedCustomerId() {
		return updatedCustomerId;
	}

	public void setUpdatedCustomerId(Integer updatedCustomerId) {
		this.updatedCustomerId = updatedCustomerId;
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
