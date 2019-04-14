package com.jmsilla.crmapitest.api.controllers;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.*;

import com.jmsilla.crmapitest.api.resources.UserResource;
import com.jmsilla.crmapitest.domain.entities.User;
import com.jmsilla.crmapitest.persistence.entitymanager.PostgreSQLEntityManagerFactory;
import com.jmsilla.crmapitest.persistence.repositories.PostgreSQLUserRepository;

@RestController
@RequestMapping("/users")
public class UserController {
	@GetMapping
	public List<UserResource> getAllUsers() {
		PostgreSQLUserRepository repo = getRepository();
		return repo.findAll().stream().map(UserController::map)
				.collect(Collectors.toList());
	}

	private PostgreSQLUserRepository getRepository() {
		return new PostgreSQLUserRepository(
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
		PostgreSQLUserRepository repo = getRepository();
		
		return map(repo.findById(id));
	}
	
	@PostMapping
	public UserResource createUser(@RequestBody UserResource user) {
		PostgreSQLUserRepository repo = getRepository();
		
		Integer id = repo.getNextId();
		
		User newUser = User.createUser(id, user.getName());
		
		repo.create(newUser);
		
		return map(newUser);
	}
}
