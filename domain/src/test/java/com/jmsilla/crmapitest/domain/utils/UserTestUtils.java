package com.jmsilla.crmapitest.domain.utils;

import com.jmsilla.crmapitest.domain.entities.User;

public class UserTestUtils {
	public static User testUser() {
		return new User("user", false);
	}
}
