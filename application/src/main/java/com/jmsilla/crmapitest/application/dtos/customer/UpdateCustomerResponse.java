package com.jmsilla.crmapitest.application.dtos.customer;

import com.jmsilla.crmapitest.application.dtos.BaseResponse;

public class UpdateCustomerResponse extends BaseResponse {
	private Integer updatedCustomerId;

	public Integer getUpdatedCustomerId() {
		return updatedCustomerId;
	}

	public void setUpdatedCustomerId(Integer updatedCustomerId) {
		this.updatedCustomerId = updatedCustomerId;
	}
}
