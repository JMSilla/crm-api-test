package com.jmsilla.crmapitest.application.usecases;

import com.jmsilla.crmapitest.application.dtos.*;
import com.jmsilla.crmapitest.domain.entities.User;
import com.jmsilla.crmapitest.domain.exceptions.DomainException;
import com.jmsilla.crmapitest.domain.repositories.UserRepository;

public class CreateUserUseCase {
	private UserRepository userRepository;
	
	public CreateUserUseCase(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public CreateUserResponse execute(CreateUserRequest request) {
		CreateUserResponse response = new CreateUserResponse();
		
		User requestingUser = userRepository.findByName(request.getRequestingUser());
		
		if (requestingUser == null || !requestingUser.isAdmin()) {
			response.setError(true);
			response.setErrorCode("error.authorization");
			return response;
		}
		
		User existingUser = userRepository.findByName(request.getName());
		
		if (existingUser != null) {
			response.setError(true);
			response.setErrorCode("user.existsWithSameName");
			return response;
		}
		
		try {
			User newUser = User.createUser(userRepository.getNextId(), 
					request.getName());
			
			if (request.getIsAdmin())
				newUser.setAsAdmin();
			
			userRepository.create(newUser);
			
			response.setCreatedUserId(newUser.getId());
		}
		catch(DomainException ex) {
			response.setError(true);
			response.setErrorCode(ex.getMessage());
		}
		
		return response;
	}
}
