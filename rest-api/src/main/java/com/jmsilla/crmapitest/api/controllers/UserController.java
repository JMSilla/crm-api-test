package com.jmsilla.crmapitest.api.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.jmsilla.crmapitest.api.resources.*;
import com.jmsilla.crmapitest.api.utils.*;
import com.jmsilla.crmapitest.application.dtos.user.*;
import com.jmsilla.crmapitest.application.usecases.user.*;
import com.jmsilla.crmapitest.persistence.entitymanager.PostgreSQLEntityManagerFactory;
import com.jmsilla.crmapitest.persistence.repositories.PostgreSQLUserRepository;

@RestController
@RequestMapping(value = "/users", headers = "Accept=application/json")
public class UserController {
	@GetMapping
	public ResponseEntity<Object> getAllUsers() {
		ListUsersUseCase useCase = new ListUsersUseCase(getRepository());
		
		ListUsersRequest request = new ListUsersRequest();
		request.setRequestingUser("pepe");
		
		ListUsersResponse response = useCase.execute(request);
		
		if (response.hasError()) {
			return ErrorResponseUtils.createErrorResponse(
					response.getErrorMessage());
		}
		
		List<UserResourceWithId> resources = response.getUsers().stream()
				.map(UserMappers::mapToUserResource)
				.collect(Collectors.toList());
		
		return new ResponseEntity<>(resources, HttpStatus.OK);
	}

	private PostgreSQLUserRepository getRepository() {
		return new PostgreSQLUserRepository(
				PostgreSQLEntityManagerFactory.createEntityManagerFactory()
				.createEntityManager());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getUser(@PathVariable Integer id) {
		GetUserUseCase useCase = new GetUserUseCase(getRepository());
		
		GetUserRequest request = new GetUserRequest();
		
		request.setRequestingUser("admin");
		request.setUserId(id);
		
		GetUserResponse response = useCase.execute(request);
		
		if (response.hasError()) {
			return ErrorResponseUtils.createErrorResponse(
					response.getErrorMessage(), id);
		}
		
		return new ResponseEntity<>(UserMappers.mapToUserResource(response), 
				HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Object> createUser(
			@RequestBody UserResource createUserResource) 
	{
		CreateUserUseCase useCase = new CreateUserUseCase(getRepository());

		CreateUserRequest request = new CreateUserRequest();
		
		request.setRequestingUser("admin");
		request.setName(createUserResource.getName());
		request.setIsAdmin(createUserResource.getAdmin());

		CreateUserResponse response = useCase.execute(request);

		if (response.hasError()) {
			return ErrorResponseUtils.createErrorResponse(
					response.getErrorMessage());
		}
		
		return new ResponseEntity<>(UserMappers.mapToUserResource(request, response),
				HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateUser(@PathVariable Integer id,
			@RequestBody UserResource updateUserResource)
	{
		UpdateUserUseCase useCase = new UpdateUserUseCase(getRepository());

		UpdateUserRequest request = new UpdateUserRequest();
		
		request.setRequestingUser("admin");
		request.setId(id);
		request.setName(updateUserResource.getName());
		request.setIsAdmin(updateUserResource.getAdmin());

		UpdateUserResponse response = useCase.execute(request);
		
		if (response.hasError()) {
			return ErrorResponseUtils.createErrorResponse(
					response.getErrorMessage(), id);
		}
		
		UserResourceWithId userResponse = UserMappers.mapToUserResourceWithId(response);
		
		return new ResponseEntity<>(userResponse, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable Integer id) {
		DeleteUserUseCase useCase = new DeleteUserUseCase(getRepository());
		DeleteUserRequest request = new DeleteUserRequest();
		
		request.setRequestingUser("admin");
		request.setUserId(id);
		
		DeleteUserResponse response = useCase.execute(request);
		
		if (response.hasError()) {
			return ErrorResponseUtils.createErrorResponse(
					response.getErrorMessage(), id);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
