package com.jmsilla.crmapitest.domain.entities;

import com.jmsilla.crmapitest.domain.exceptions.*;

public class User {
	private String name;
	private Boolean isAdmin;
	
	public User(String name, Boolean isAdmin) {
		if (name == null || name.trim().isEmpty())
			throw new RequiredFieldException("name");
		
		if (name.length() > 30)
			throw new LengthExceededException("name");
		
		this.name = name;
		this.isAdmin = isAdmin;
	}

	public String getName() {
		return name;
	}

	public Boolean isAdmin() {
		return isAdmin;
	}
}
