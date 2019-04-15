package com.jmsilla.crmapitest.persistence.repositories;

import com.jmsilla.crmapitest.domain.entities.*;
import com.jmsilla.crmapitest.domain.valueobjects.Image;
import com.jmsilla.crmapitest.persistence.entities.*;

class Mappers {
	static User mapToUser(UserEntity userEntity) {
		if (userEntity == null)
			return null;
		
		User user = User.createUser(userEntity.getId(), userEntity.getName());
		
		if (userEntity.getAdmin() != null && userEntity.getAdmin())
			user.setAsAdmin();
		else
			user.setAsNormalUser();
		
		return user;
	}
	
	static UserEntity mapToUserEntity(User user) {
		if (user == null)
			return null;
		
		UserEntity userEntity = new UserEntity();
		
		userEntity.setId(user.getId());
		userEntity.setName(user.getName());
		userEntity.setAdmin(user.isAdmin());
		
		return userEntity;
	}
	
	static Customer mapToCustomer(CustomerEntity customerEntity) {
		if (customerEntity == null)
			return null;
		
		Customer customer = Customer.create(customerEntity.getId(), 
				customerEntity.getName(), customerEntity.getSurname(), 
				Mappers.mapToUser(customerEntity.getCreatedByUser()));
		
		UserEntity modifiedByUser = customerEntity.getModifiedByUser();
		
		if (customerEntity.getPhoto() != null) {
			Image image = new Image(customerEntity.getPhoto());
			
			image.changeMimeType(customerEntity.getPhotoMimeType());
			
			customer.changePhoto(image, Mappers.mapToUser(
					modifiedByUser));
		}
		
		if (modifiedByUser != null) {
			customer.changeName(customerEntity.getName(), 
					Mappers.mapToUser(modifiedByUser));
		}
		
		return customer;
	}
}
