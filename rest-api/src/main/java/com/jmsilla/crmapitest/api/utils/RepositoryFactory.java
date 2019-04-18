package com.jmsilla.crmapitest.api.utils;

import com.jmsilla.crmapitest.domain.repositories.*;
import com.jmsilla.crmapitest.persistence.entitymanager.PostgreSQLEntityManagerFactory;
import com.jmsilla.crmapitest.persistence.repositories.*;

public class RepositoryFactory {
	public static UserRepository getUserRepository() {
		return new PostgreSQLUserRepository(
				PostgreSQLEntityManagerFactory.createEntityManagerFactory()
				.createEntityManager());
	}
	
	public static CustomerRepository getCustomerRepository() {
		return new PostgreSQLCustomerRepository(
				PostgreSQLEntityManagerFactory.createEntityManagerFactory()
				.createEntityManager());
	}
}
