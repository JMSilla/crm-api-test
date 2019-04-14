package com.jmsilla.crmapitest.api.controllers;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.*;

import com.jmsilla.crmapitest.api.resources.UserResource;
import com.jmsilla.crmapitest.domain.entities.User;
import com.jmsilla.crmapitest.persistence.repositories.PostgreSQLUserRepository;

@RestController
@RequestMapping("/users")
public class UserController {
	@GetMapping
	public List<UserResource> getAllUsers() {
		PostgreSQLUserRepository repo = new PostgreSQLUserRepository();
		return repo.findAll().stream().map(UserController::map)
				.collect(Collectors.toList());
	}
	
	private static UserResource map(User user) {
		UserResource resource = new UserResource();
		
		resource.setId(user.getId());
		resource.setName(user.getName());
		
		return resource;
	}
	
	@GetMapping("/{id}")
	public UserResource getUser(@PathVariable Integer id) {
		PostgreSQLUserRepository repo = new PostgreSQLUserRepository();
		
		return map(repo.findById(id));
	}
	
	@PostMapping
	public UserResource createUser(@RequestBody UserResource user) {
		PostgreSQLUserRepository repo = new PostgreSQLUserRepository();
		
		Integer id = repo.getNextId();
		
		User newUser = User.createUser(id, user.getName());
		
		repo.create(newUser);
		
		return map(newUser);
	}
}
