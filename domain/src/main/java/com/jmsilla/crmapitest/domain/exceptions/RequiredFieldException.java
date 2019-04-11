package com.jmsilla.crmapitest.domain.exceptions;

public class RequiredFieldException extends RuntimeException {
	private static final long serialVersionUID = -6630422071143245581L;

	public RequiredFieldException(String fieldName) {
		super(fieldName);
	}
}
