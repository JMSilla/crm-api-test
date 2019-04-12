package com.jmsilla.crmapitest.domain.entities;

import com.jmsilla.crmapitest.domain.exceptions.*;
import com.jmsilla.crmapitest.domain.valueobjects.Image;

public class Customer {
	private Integer id;
	private String name;
	private String surname;
	private Image photo;
	private String createdByUsername;
	
	private Customer() { }
	
	public static Customer create(Integer id, String name, 
			String surname, User createdByUser) 
	{
		validateId(id);
		validateName(name);
		validateSurname(surname);
		validateCreatedByUser(createdByUser);
		
		Customer customer = new Customer();
		
		customer.id = id;
		customer.name = name;
		customer.surname = surname;
		customer.createdByUsername = createdByUser.getName();
		
		return customer;
	}

	private static void validateId(Integer id) {
		if (id == null || id < 1)
			throw new RequiredFieldException("id");
	}

	private static void validateName(String name) {
		if (name == null || name.trim().isEmpty())
			throw new RequiredFieldException("name");
		
		if (name.length() > 30)
			throw new LengthExceededException("name");
	}
	
	private static void validateSurname(String surname) {
		if (surname == null || surname.trim().isEmpty())
			throw new RequiredFieldException("surname");
		
		if (surname.length() > 30)
			throw new LengthExceededException("surname");
	}
	
	private static void validateCreatedByUser(User createdByUser) {
		if (createdByUser == null)
			throw new RequiredFieldException("createdByUsername");
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public Image getPhoto() {
		return photo;
	}
	
	public String getCreatedByUsername() {
		return createdByUsername;
	}
	
	public void changeName(String name) {
		validateName(name);
		
		this.name = name;
	}
	
	public void changeSurname(String surname) {
		validateSurname(surname);
		
		this.surname = surname;
	}
	
	public void changePhoto(Image image) {
		this.photo = image;
	}
}
