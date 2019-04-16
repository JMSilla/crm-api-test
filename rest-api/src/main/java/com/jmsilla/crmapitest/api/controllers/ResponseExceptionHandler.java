package com.jmsilla.crmapitest.api.controllers;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jmsilla.crmapitest.api.utils.ErrorResponseUtils;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler
	protected ResponseEntity<Object> handleConflict(Exception ex, 
			WebRequest request) 
	{
		return ErrorResponseUtils.createErrorResponse(HttpStatus.BAD_REQUEST, 
				ex.getMessage());
	}
}