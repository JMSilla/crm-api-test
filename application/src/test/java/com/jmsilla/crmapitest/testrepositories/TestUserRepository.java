package com.jmsilla.crmapitest.testrepositories;

import java.util.*;
import java.util.stream.Collectors;

import com.jmsilla.crmapitest.domain.entities.User;
import com.jmsilla.crmapitest.domain.repositories.UserRepository;

public class TestUserRepository implements UserRepository{
	private Map<Integer, User> usersById = new TreeMap<>();
	private Map<String, User> usersByName = new HashMap<>();
	private List<String> calls = new ArrayList<>();
	
	public Integer getNextId() {
		calls.add("getNextId()");
		return usersById.keySet().stream().mapToInt(i -> i)
				.max().orElse(1) + 1;
	}

	public List<User> findAll() {
		calls.add("findAll()");
		return usersById.values().stream().collect(Collectors.toList());
	}

	public User findById(Integer userId) {
		calls.add("findById(" + userId + ")");
		return usersById.get(userId);
	}

	public void create(User user) {
		calls.add("create(" + user.getId() + ")");
		usersById.put(user.getId(), user);
		usersByName.put(user.getName(), user);
	}

	public void update(User user) {
		calls.add("update(" + user.getId() + ")");
		usersById.put(user.getId(), user);
		usersByName.put(user.getName(), user);
	}

	public void delete(User user) {
		calls.add("delete(" + user.getId() + ")");
		usersById.remove(user.getId());
		usersByName.remove(user.getName());
	}

	public User findByName(String name) {
		calls.add("findByName(" + name + ")");
		return usersByName.get(name);
	}
	
	public List<String> getCalls() {
		return calls;
	}
	
	public String getLastCall() {
		if (calls.size() == 0)
			return null;
		
		return calls.get(calls.size() - 1);
	}
}
