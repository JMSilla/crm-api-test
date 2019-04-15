package com.jmsilla.crmapitest.api.controllers;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.*;

import com.jmsilla.crmapitest.api.resources.UserResource;
import com.jmsilla.crmapitest.application.dtos.user.*;
import com.jmsilla.crmapitest.application.usecases.user.*;
import com.jmsilla.crmapitest.domain.entities.*;
import com.jmsilla.crmapitest.persistence.entitymanager.PostgreSQLEntityManagerFactory;
import com.jmsilla.crmapitest.persistence.repositories.*;

@RestController
@RequestMapping("/users")
public class UserController {
	@GetMapping
	public List<UserResource> getAllUsers() {
		ListUsersUseCase useCase = new ListUsersUseCase(getRepository());
		
		ListUsersRequest request = new ListUsersRequest();
		request.setRequestingUser("admin");
		
		ListUsersResponse response = useCase.execute(request);
		
		List<UserResource> resources = response.getUsers().stream()
				.map(UserController::mapToUserResource)
				.collect(Collectors.toList());
		
		return resources;
	}

	private static UserResource mapToUserResource(GetUserResponse u) {
		UserResource resource = new UserResource();
		
		resource.setId(u.getUserId());
		resource.setName(u.getUserName());
		
		return resource;
	}

	private PostgreSQLUserRepository getRepository() {
		return new PostgreSQLUserRepository(
				PostgreSQLEntityManagerFactory.createEntityManagerFactory()
				.createEntityManager());
	}
	
	private PostgreSQLCustomerRepository getCustomerRepository() {
		return new PostgreSQLCustomerRepository(
				PostgreSQLEntityManagerFactory.createEntityManagerFactory()
				.createEntityManager());
	}
	
	private static UserResource map(User user) {
		UserResource resource = new UserResource();
		
		resource.setId(user.getId());
		resource.setName(user.getName());
		
		return resource;
	}
	
	@GetMapping("/{id}")
	public UserResource getUser(@PathVariable Integer id) {
		GetUserUseCase useCase = new GetUserUseCase(getRepository());
		
		GetUserRequest request = new GetUserRequest();
		request.setRequestingUser("admin");
		request.setUserId(id);
		
		GetUserResponse response = useCase.execute(request);
		
		return mapToUserResource(response);
	}
	
	@PostMapping
	public UserResource createUser(@RequestBody UserResource user) {
		PostgreSQLUserRepository repo = getRepository();
		
		Integer id = repo.getNextId();
		
		User newUser = User.createUser(id, user.getName());
		
		repo.create(newUser);
		
		Customer customer = Customer.create(getCustomerRepository().getNextId(), 
				"Hola", "surname", newUser);
		
		getCustomerRepository().create(customer);
		
		return map(newUser);
	}
}
