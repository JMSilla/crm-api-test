package com.jmsilla.crmapitest.domain.exceptions;

public class DomainException extends RuntimeException {
	private static final long serialVersionUID = 7425199801196619673L;

	public DomainException(String message) {
		super(message);
	}
}
