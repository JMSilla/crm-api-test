package com.jmsilla.crmapitest.application.usecases.customer;

import com.jmsilla.crmapitest.application.dtos.customer.*;
import com.jmsilla.crmapitest.domain.entities.Customer;
import com.jmsilla.crmapitest.domain.repositories.CustomerRepository;
import com.jmsilla.crmapitest.domain.valueobjects.Image;

public class GetCustomerPhotoUseCase {
private CustomerRepository customerRepository;
	
	public GetCustomerPhotoUseCase(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public GetCustomerPhotoResponse execute(GetCustomerPhotoRequest request) {
		GetCustomerPhotoResponse response = new GetCustomerPhotoResponse();
		
		Customer customer = customerRepository.findById(request.getCustomerId());
		
		if (customer == null) {
			response.setError(true);
			response.setErrorMessage("customer.doesntExists");
			return response;
		}
		
		Image photo = customer.getPhoto();
		
		if (photo == null) {
			response.setError(true);
			response.setErrorMessage("customer.photo.doesntExists");
			return response;
		}
		
		response.setMimeType(photo.getMimeType());
		response.setContent(photo.getImageContent());
		
		return response;
	}
}
