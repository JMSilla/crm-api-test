package com.jmsilla.crmapitest.domain.entities;

import com.jmsilla.crmapitest.domain.exceptions.*;

public class User {
	private Integer id;
	private String name;
	private Boolean isAdmin;
	
	public static User createUser(Integer id, String name) {
		validateId(id);
		validateName(name);
		
		User user = new User();
		
		user.id = id;
		user.name = name;
		user.isAdmin = false;
		
		return user;
	}
	
	public static User createAdminUser(Integer id, String name) {
		User adminUser = createUser(id, name);
		
		adminUser.isAdmin = true;
		
		return adminUser;
	}

	private static void validateId(Integer id) {
		if (id == null || id < 1) {
			throw new RequiredFieldException("id");
		}
	}

	private static void validateName(String name) {
		if (name == null || name.trim().isEmpty())
			throw new RequiredFieldException("name");
		
		if (name.length() > 30)
			throw new LengthExceededException("name");
	}

	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public Boolean isAdmin() {
		return isAdmin;
	}
	
	public void changeName(String name) {
		validateName(name);
		
		this.name = name;
	}
	
	public void setAsAdmin() {
		isAdmin = true;
	}
	
	public void setAsNormalUser() {
		isAdmin = false;
	}
}
