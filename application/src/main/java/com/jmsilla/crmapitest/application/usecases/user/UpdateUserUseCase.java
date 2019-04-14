package com.jmsilla.crmapitest.application.usecases.user;

import com.jmsilla.crmapitest.application.dtos.user.*;
import com.jmsilla.crmapitest.domain.entities.User;
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
		User userWithSameName = userRepository.findByName(request.getName());
		
		if (existsDifferentUserWithSameName(user, userWithSameName)) {
			response.setError(true);
			response.setErrorMessage("user.existsWithSameName");
			return response;
		}
		
		user.changeName(request.getName());
		
		user.setAsNormalUser();
		
		if (request.getIsAdmin())
			user.setAsAdmin();
		
		userRepository.update(user);
		
		response.setUpdatedUserId(user.getId());
		
		return response;
	}

	private boolean existsDifferentUserWithSameName(User user, User userWithSameName) {
		return userWithSameName != null 
			&& !userWithSameName.getId().equals(user.getId());
	}
}
