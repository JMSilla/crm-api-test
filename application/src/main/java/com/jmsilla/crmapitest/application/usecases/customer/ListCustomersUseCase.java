package com.jmsilla.crmapitest.application.usecases.customer;

import java.util.List;
import java.util.stream.Collectors;

import com.jmsilla.crmapitest.application.dtos.customer.*;
import com.jmsilla.crmapitest.domain.entities.Customer;
import com.jmsilla.crmapitest.domain.repositories.*;

public class ListCustomersUseCase {
	private CustomerRepository customerRepository;

	public ListCustomersUseCase(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	public ListCustomersResponse execute() {
		ListCustomersResponse response = new ListCustomersResponse();
		
		List<Customer> customers = customerRepository.findAll();
		
		response.setCustomers(customers.stream().map(ListCustomersUseCase::mapCustomer)
				.collect(Collectors.toList()));
		
		return response;
	}
	
	private static CustomerResponse mapCustomer(Customer customer) {
		CustomerResponse customerResponse = new CustomerResponse();
		
		customerResponse.setId(customer.getId());
		customerResponse.setName(customer.getName());
		customerResponse.setSurname(customer.getSurname());
		
		return customerResponse;
	}
}
