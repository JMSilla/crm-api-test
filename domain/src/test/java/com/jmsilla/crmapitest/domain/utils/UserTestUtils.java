package com.jmsilla.crmapitest.domain.utils;

import com.jmsilla.crmapitest.domain.entities.User;

public class UserTestUtils {
	public static User testUser() {
		return testUser("name");
	}
	
	public static User testUser(String username) {
		return new User(username, false);
	}
}
