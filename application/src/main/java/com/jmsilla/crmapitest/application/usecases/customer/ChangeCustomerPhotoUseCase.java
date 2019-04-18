package com.jmsilla.crmapitest.application.usecases.customer;

import com.jmsilla.crmapitest.application.dtos.customer.*;
import com.jmsilla.crmapitest.domain.entities.*;
import com.jmsilla.crmapitest.domain.repositories.*;
import com.jmsilla.crmapitest.domain.valueobjects.Image;

public class ChangeCustomerPhotoUseCase {
	private UserRepository userRepository;
	private CustomerRepository customerRepository;

	private static final int BYTES_5MB = 5242880; 
	
	public ChangeCustomerPhotoUseCase(UserRepository userRepository, 
			CustomerRepository customerRepository) 
	{
		this.userRepository = userRepository;
		this.customerRepository = customerRepository;
	}
	
	public ChangeCustomerPhotoResponse execute(ChangeCustomerPhotoRequest request) {
		ChangeCustomerPhotoResponse response = new ChangeCustomerPhotoResponse();

		Customer customer = customerRepository.findById(request.getCustomerId());
		
		if (customer == null) {
			response.setError(true);
			response.setErrorMessage("customer.doesntExists");
			return response;
		}
		
		if (mimeTypeIsNotImage(request)) {
			response.setError(true);
			response.setErrorMessage("customer.photo.wrongImageType");
			return response;
		}
		
		if (imageSizeIsTooLarge(request)) {
			response.setError(true);
			response.setErrorMessage("customer.photo.imageTooLarge");
			return response;
		}
		
		User user = userRepository.findByName(request.getRequestingUser());
		
		Image newPhoto = new Image(request.getPhotoContent());		
		newPhoto.changeMimeType(request.getMimeType());
		
		customer.changePhoto(newPhoto, user);
		
		customerRepository.update(customer);
		
		return response;
	}

	private boolean mimeTypeIsNotImage(ChangeCustomerPhotoRequest request) {
		return request.getMimeType() == null 
			|| !request.getMimeType().startsWith("image");
	}
	
	private boolean imageSizeIsTooLarge(ChangeCustomerPhotoRequest request) {
		return request.getPhotoContent() == null
			|| request.getPhotoContent().length > BYTES_5MB;
	}
}
