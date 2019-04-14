package com.jmsilla.crmapitest.application.dtos.customer;

import com.jmsilla.crmapitest.application.dtos.BaseResponse;

public class CreateCustomerResponse extends BaseResponse {
	private Integer createdCustomerId;

	public Integer getCreatedCustomerId() {
		return createdCustomerId;
	}

	public void setCreatedCustomerId(Integer createdCustomerId) {
		this.createdCustomerId = createdCustomerId;
	}
}
