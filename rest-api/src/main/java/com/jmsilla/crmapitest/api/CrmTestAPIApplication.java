package com.jmsilla.crmapitest.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrmTestAPIApplication {
	public static void main(String[] args) {
		// Environment variable: SERVER_SERVLET_CONTEXT_PATH
	    System.setProperty("server.servlet.context-path", "/crmtestapi");
		SpringApplication.run(CrmTestAPIApplication.class, args);
	}
}
