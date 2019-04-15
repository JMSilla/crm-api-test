package com.jmsilla.crmapitest.application.dtos.customer;

import com.jmsilla.crmapitest.application.dtos.BaseResponse;

public class DeleteCustomerResponse extends BaseResponse {
	private Integer deletedCustomerId;

	public Integer getDeletedCustomerId() {
		return deletedCustomerId;
	}

	public void setDeletedCustomerId(Integer deletedCustomerId) {
		this.deletedCustomerId = deletedCustomerId;
	}
}
