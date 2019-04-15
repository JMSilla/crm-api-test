package com.jmsilla.crmapitest.application.usecases.customer;

import com.jmsilla.crmapitest.application.dtos.customer.*;
import com.jmsilla.crmapitest.domain.entities.Customer;
import com.jmsilla.crmapitest.domain.repositories.*;

public class GetCustomerDetailsUseCase {
	private CustomerRepository customerRepository;
	
	public GetCustomerDetailsUseCase(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public GetCustomerDetailsResponse execute(GetCustomerDetailsRequest request) {
		GetCustomerDetailsResponse response = new GetCustomerDetailsResponse();
		
		Customer customer = customerRepository.findById(request.getId());
		
		if (customer == null) {
			response.setError(true);
			response.setErrorMessage("customer.doesntExists");
			return response;
		}
		
		response.setId(customer.getId());
		response.setName(customer.getName());
		response.setSurname(customer.getSurname());
		response.setCreatedByUsername(customer.getCreatedByUsername());
		response.setModifiedByUsername(customer.getModifiedByUsername());
		
		return response;
	}
}
