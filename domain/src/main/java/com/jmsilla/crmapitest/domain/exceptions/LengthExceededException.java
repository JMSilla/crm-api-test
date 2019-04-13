package com.jmsilla.crmapitest.domain.exceptions;

public class LengthExceededException extends DomainException {
	private static final long serialVersionUID = 25814286193623224L;

	public LengthExceededException(String fieldName) {
		super(fieldName + ".lengthExceeded");
	}
}
