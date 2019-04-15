package com.jmsilla.crmapitest.application.dtos.customer;

import java.util.List;

import com.jmsilla.crmapitest.application.dtos.BaseResponse;

public class ListCustomersResponse extends BaseResponse {
	private List<CustomerResponse> customers;

	public List<CustomerResponse> getCustomers() {
		return customers;
	}

	public void setCustomers(List<CustomerResponse> customers) {
		this.customers = customers;
	}
}
