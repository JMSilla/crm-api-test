package com.jmsilla.crmapitest.application.usecases.user;

import com.jmsilla.crmapitest.application.dtos.user.*;
import com.jmsilla.crmapitest.domain.entities.User;
import com.jmsilla.crmapitest.domain.repositories.UserRepository;

public class GetUserUseCase {
	private UserRepository userRepository;

	public GetUserUseCase(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public GetUserResponse execute(GetUserRequest request) {
		GetUserResponse response = new GetUserResponse();
		
		User requestingUser = userRepository.findByName(request.getRequestingUser());
		
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
		
		response.setUserId(user.getId());
		response.setUserName(user.getName());
		response.setIsAdmin(user.isAdmin());
		
		return response;
	}
}
