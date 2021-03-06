package com.jmsilla.crmapitest.application.usecases.user;

import com.jmsilla.crmapitest.application.dtos.user.*;
import com.jmsilla.crmapitest.domain.entities.User;
import com.jmsilla.crmapitest.domain.exceptions.DomainException;
import com.jmsilla.crmapitest.domain.repositories.UserRepository;

public class UpdateUserUseCase {
	private UserRepository userRepository;
	
	public UpdateUserUseCase(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public UpdateUserResponse execute(UpdateUserRequest request) {
		UpdateUserResponse response = new UpdateUserResponse();
		
		User requestingUser = userRepository.findByName(request.getRequestingUser());
		
		if (requestingUser == null || !requestingUser.isAdmin()) {
			response.setError(true);
			response.setErrorMessage("error.authorization");
			return response;
		}
		
		User user = userRepository.findById(request.getId());
		
		if (user == null) {
			response.setError(true);
			response.setErrorMessage("user.doesntExists");
			return response;
		}
		
		User userWithSameName = userRepository.findByName(request.getName());
		
		if (existsDifferentUserWithSameName(user, userWithSameName)) {
			response.setError(true);
			response.setErrorMessage("user.existsWithSameName");
			return response;
		}
		
		try {
			if (request.getName() != null)
				user.changeName(request.getName());
			
			if (request.getIsAdmin() != null) {
				user.setAsNormalUser();
				
				if (request.getIsAdmin())
					user.setAsAdmin();
			}
			
			userRepository.update(user);
			
			response.setUpdatedUserId(user.getId());
			response.setName(user.getName());
			response.setAdmin(user.isAdmin());
		}
		catch(DomainException ex) {
			response.setError(true);
			response.setErrorMessage(ex.getMessage());
		}
		
		return response;
	}

	private boolean existsDifferentUserWithSameName(User user, User userWithSameName) {
		return userWithSameName != null 
			&& !userWithSameName.getId().equals(user.getId());
	}
}
