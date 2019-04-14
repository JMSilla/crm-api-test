package com.jmsilla.crmapitest.application.usecases.user;

import com.jmsilla.crmapitest.application.dtos.user.*;
import com.jmsilla.crmapitest.domain.entities.User;
import com.jmsilla.crmapitest.domain.repositories.UserRepository;

public class DeleteUserUseCase {
	private UserRepository userRepository;
	
	public DeleteUserUseCase(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public DeleteUserResponse execute(DeleteUserRequest request) {
		DeleteUserResponse response = new DeleteUserResponse();
		
		User requestingUser = userRepository.findByName(
				request.getRequestingUser());
		
		if (requestingUser == null || !requestingUser.isAdmin()) {
			response.setError(true);
			response.setErrorMessage("error.authorization");
			return response;
		}
		
		User user = userRepository.findById(request.getUserId());
		
		if (user == null) {
			response.setError(true);
			response.setErrorMessage("user.doesntExists");
			return response;
		}
		
		userRepository.delete(user);
		
		response.setDeletedUserId(user.getId());
		
		return response;
	}
	
}
