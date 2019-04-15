package com.jmsilla.crmapitest.application.usecases.customer;

import com.jmsilla.crmapitest.application.dtos.customer.*;
import com.jmsilla.crmapitest.domain.entities.Customer;
import com.jmsilla.crmapitest.domain.repositories.CustomerRepository;

public class DeleteCustomerUseCase {
	private CustomerRepository customerRepository;

	public DeleteCustomerUseCase(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	public DeleteCustomerResponse execute(DeleteCustomerRequest request) {
		DeleteCustomerResponse response = new DeleteCustomerResponse();
		
		Customer customer = customerRepository.findById(request.getId());
		
		if (customer == null) {
			response.setError(true);
			response.setErrorMessage("customer.doesntExists");
			return response;
		}
		
		customerRepository.delete(customer);
		
		response.setDeletedCustomerId(customer.getId());
		
		return response;
	}
}
