package com.jmsilla.crmapitest.testrepositories;

import java.util.*;
import java.util.stream.Collectors;

import com.jmsilla.crmapitest.domain.entities.User;
import com.jmsilla.crmapitest.domain.repositories.UserRepository;

public class TestUserRepository implements UserRepository{
	private Map<Integer, User> usersById = new TreeMap<>();
	private Map<String, User> usersByName = new HashMap<>();
	
	public Integer getNextId() {
		return usersById.keySet().stream().mapToInt(i -> i)
				.max().orElse(1) + 1;
	}

	public List<User> findAll() {
		return usersById.values().stream().collect(Collectors.toList());
	}

	public User findById(Integer userId) {
		return usersById.get(userId);
	}

	public void create(User user) {
		usersById.put(user.getId(), user);
		usersByName.put(user.getName(), user);
	}

	public void update(User user) {
		usersById.put(user.getId(), user);
		usersByName.put(user.getName(), user);
	}

	public void delete(User user) {
		usersById.remove(user.getId());
		usersByName.remove(user.getName());
	}

	public User findByName(String name) {
		return usersByName.get(name);
	}
}
