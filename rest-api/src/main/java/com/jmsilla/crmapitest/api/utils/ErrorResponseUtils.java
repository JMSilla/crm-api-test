package com.jmsilla.crmapitest.api.utils;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import org.springframework.http.*;

import com.jmsilla.crmapitest.api.resources.ErrorResponse;

public class ErrorResponseUtils {
	public static ResponseEntity<Object> createErrorResponse(
			String errorMessage, Object... args)
	{
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		if (errorMessage.equals("error.authorization"))
			status = HttpStatus.UNAUTHORIZED;
		
		return createErrorResponse(status, errorMessage, args);
	}
	
	public static ResponseEntity<Object> createErrorResponse(HttpStatus httpStatus, 
			String errorMessage, Object... args) 
	{
		ErrorResponse error = new ErrorResponse();
		
		error.setTimestamp(LocalDateTime.now());
		error.setStatus(httpStatus.value());
		ResourceBundle bundle = ResourceBundle.getBundle("messages");
		error.setError(MessageFormat.format(bundle.getString(errorMessage), args));
		
		return new ResponseEntity<>(error, httpStatus);
	}
}
