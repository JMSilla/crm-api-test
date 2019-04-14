package com.jmsilla.crmapitest.api.controllers;

import java.util.*;

import org.springframework.web.bind.annotation.*;

import com.jmsilla.crmapitest.api.resources.UserResource;

@RestController
@RequestMapping("/users")
public class UserController {
	private static Map<Integer, UserResource> USERS = new HashMap<>();
	
	@GetMapping
	public Collection<UserResource> getAllUsers() {
		return USERS.values();
	}
	
	@GetMapping("/{id}")
	public UserResource getUser(@PathVariable Integer id) {
		return USERS.get(id);
	}
	
	@PostMapping
	public UserResource createUser(@RequestBody UserResource user) {
		Integer id = USERS.size() + 1;
		
		user.setId(id);
		USERS.put(id, user);
		
		return user;
	}
}
