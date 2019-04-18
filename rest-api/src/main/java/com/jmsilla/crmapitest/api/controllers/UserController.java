package com.jmsilla.crmapitest.api.controllers;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.jmsilla.crmapitest.api.resources.*;
import com.jmsilla.crmapitest.api.utils.*;
import com.jmsilla.crmapitest.application.dtos.user.*;
import com.jmsilla.crmapitest.application.usecases.user.*;

@RestController
@RequestMapping(value = "/users", headers = "Accept=application/json")
public class UserController {
	@GetMapping
	public ResponseEntity<Object> getAllUsers(Principal principal) {
		ListUsersUseCase useCase = new ListUsersUseCase(
				RepositoryFactory.getUserRepository());
		
		ListUsersRequest request = new ListUsersRequest();
		
		request.setRequestingUser(principal.getName());
		
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

	@GetMapping("/{id}")
	public ResponseEntity<Object> getUser(Principal principal, @PathVariable Integer id) {
		GetUserUseCase useCase = new GetUserUseCase(
				RepositoryFactory.getUserRepository());
		
		GetUserRequest request = new GetUserRequest();
		
		request.setRequestingUser(principal.getName());
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
	public ResponseEntity<Object> createUser(Principal principal, 
			@RequestBody UserResource createUserResource) 
	{
		CreateUserUseCase useCase = new CreateUserUseCase(
				RepositoryFactory.getUserRepository());

		CreateUserRequest request = new CreateUserRequest();
		
		request.setRequestingUser(principal.getName());
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
	public ResponseEntity<Object> updateUser(Principal principal, 
			@PathVariable Integer id,
			@RequestBody UserResource updateUserResource)
	{
		UpdateUserUseCase useCase = new UpdateUserUseCase(
				RepositoryFactory.getUserRepository());

		UpdateUserRequest request = new UpdateUserRequest();
		
		request.setRequestingUser(principal.getName());
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
	public ResponseEntity<Object> deleteUser(Principal principal,
			@PathVariable Integer id) 
	{
		DeleteUserUseCase useCase = new DeleteUserUseCase(
				RepositoryFactory.getUserRepository());
		DeleteUserRequest request = new DeleteUserRequest();
		
		request.setRequestingUser(principal.getName());
		request.setUserId(id);
		
		DeleteUserResponse response = useCase.execute(request);
		
		if (response.hasError()) {
			return ErrorResponseUtils.createErrorResponse(
					response.getErrorMessage(), id);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
