package com.jmsilla.crmapitest.application.usecases.customer;

import com.jmsilla.crmapitest.application.dtos.customer.*;
import com.jmsilla.crmapitest.domain.entities.*;
import com.jmsilla.crmapitest.domain.exceptions.DomainException;
import com.jmsilla.crmapitest.domain.repositories.*;

public class UpdateCustomerUseCase {
	private CustomerRepository customerRepository;
	private UserRepository userRepository;

	public UpdateCustomerUseCase(CustomerRepository customerRepository,
			UserRepository userRepository) 
	{
		this.customerRepository = customerRepository;
		this.userRepository = userRepository;
	}
	
	public UpdateCustomerResponse execute(UpdateCustomerRequest request) {
		UpdateCustomerResponse response = new UpdateCustomerResponse();
		
		Customer customer = customerRepository.findById(request.getId());
		
		if (customer == null) {
			response.setError(true);
			response.setErrorMessage("customer.doesntExists");
			return response;
		}
		
		User modifyingUser = userRepository.findByName(request.getRequestingUser());

		try {
			if (request.getName() != null)
				customer.changeName(request.getName(), modifyingUser);
			
			if (request.getSurname() != null)
				customer.changeSurname(request.getSurname(), modifyingUser);
			
			customerRepository.update(customer);
			
			response.setUpdatedCustomerId(customer.getId());
			response.setName(customer.getName());
			response.setSurname(customer.getSurname());
		}
		catch(DomainException dex) {
			response.setError(true);
			response.setErrorMessage(dex.getMessage());
		}
		
		return response;
	}
}
