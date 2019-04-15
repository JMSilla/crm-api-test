package com.jmsilla.crmapitest.application.dtos.customer;

import com.jmsilla.crmapitest.application.dtos.BaseRequest;

public class GetCustomerDetailsRequest extends BaseRequest {
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
