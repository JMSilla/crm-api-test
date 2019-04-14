package com.jmsilla.crmapitest.application.usecases;

import java.util.*;

import com.jmsilla.crmapitest.application.dtos.*;
import com.jmsilla.crmapitest.domain.entities.User;
import com.jmsilla.crmapitest.domain.repositories.UserRepository;

public class ListUsersUseCase {
	private UserRepository userRepository;
	
	public ListUsersUseCase(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public ListUsersResponse execute(ListUsersRequest request) {
		ListUsersResponse response = new ListUsersResponse();
		
		User requestingUser = userRepository.findByName(request.getRequestingUser());
		
		if (requestingUser == null || !requestingUser.isAdmin()) {
			response.setError(true);
			response.setErrorMessage("error.authorization");
			return response;
		}
		
		List<User> users = userRepository.findAll();
		
		List<GetUserResponse> responseUsers = new ArrayList<>();
		
		users.forEach(u -> {
			GetUserResponse userResponse = new GetUserResponse();
			userResponse.setUserId(u.getId());
			userResponse.setUserName(u.getName());
			userResponse.setIsAdmin(u.isAdmin());
			responseUsers.add(userResponse);
		});
		
		response.setUsers(responseUsers);
		
		return response;
	}
}
