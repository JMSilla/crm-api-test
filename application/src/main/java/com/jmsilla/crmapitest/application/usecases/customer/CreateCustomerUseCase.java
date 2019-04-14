package com.jmsilla.crmapitest.application.usecases.customer;

import com.jmsilla.crmapitest.application.dtos.customer.*;
import com.jmsilla.crmapitest.domain.entities.*;
import com.jmsilla.crmapitest.domain.exceptions.DomainException;
import com.jmsilla.crmapitest.domain.repositories.*;

public class CreateCustomerUseCase {
	private UserRepository userRepository;
	private CustomerRepository customerRepository;

	public CreateCustomerUseCase(UserRepository userRepository, 
			CustomerRepository customerRepository) 
	{
		this.userRepository = userRepository;
		this.customerRepository = customerRepository;
	}

	public CreateCustomerResponse execute(CreateCustomerRequest request) {
		CreateCustomerResponse response = new CreateCustomerResponse();
		
		Integer id = customerRepository.getNextId();
		User createdByUser = userRepository.findByName(
				request.getRequestingUser());
		
		try {
			Customer customer = Customer.create(id, 
					request.getName(), request.getSurname(), 
					createdByUser);
			customerRepository.create(customer);
			
			response.setCreatedCustomerId(id);
		}
		catch(DomainException ex) {
			response.setError(true);
			response.setErrorMessage(ex.getMessage());
		}
		
		return response;
	}
}
