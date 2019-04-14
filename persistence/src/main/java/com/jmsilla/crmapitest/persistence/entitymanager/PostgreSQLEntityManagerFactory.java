package com.jmsilla.crmapitest.persistence.entitymanager;

import java.util.*;

import javax.persistence.*;

public class PostgreSQLEntityManagerFactory {
	public static EntityManagerFactory createEntityManagerFactory() {
		Map<String, String> envProperties = generateEnvironmentProperties();
		
		return Persistence.createEntityManagerFactory("crmapitestpersistence", 
				envProperties);
	}

	private static Map<String, String> generateEnvironmentProperties() {
		Map<String, String> envVariables = System.getenv();
		Map<String, String> envProperties = new HashMap<>();
		
		envProperties.put("hibernate.connection.url", 
				"jdbc:postgresql://" + envVariables.get("POSTGRES_HOST")
				+ ":" + envVariables.get("POSTGRES_PORT")
				+ "/" + envVariables.get("POSTGRES_DB"));
		envProperties.put("hibernate.connection.username",
				envVariables.get("POSTGRES_USER"));
		envProperties.put("hibernate.connection.password",
				envVariables.get("POSTGRES_PASSWORD"));
		
		return envProperties;
	}
}
