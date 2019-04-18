package com.jmsilla.crmapitest.api.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.jmsilla.crmapitest.api.utils.*;
import com.jmsilla.crmapitest.application.dtos.customer.*;
import com.jmsilla.crmapitest.application.usecases.customer.*;

@RestController
@RequestMapping(value = "/customers", headers = "Accept=application/json")
public class UserPhotoController {
	@PostMapping("/{id}/photo")
    public ResponseEntity<Object> uploadFile(@PathVariable Integer id,
    		@RequestPart MultipartFile file) throws IOException 
	{
		ChangeCustomerPhotoUseCase useCase = new ChangeCustomerPhotoUseCase(
				RepositoryFactory.getUserRepository(), 
				RepositoryFactory.getCustomerRepository());
		ChangeCustomerPhotoRequest request = new ChangeCustomerPhotoRequest();
		
		request.setRequestingUser("admin");
		request.setCustomerId(id);
		request.setMimeType(file.getContentType());
		request.setPhotoContent(file.getBytes());

		ChangeCustomerPhotoResponse response = useCase.execute(request);
		
		if (response.hasError()) {
			return ErrorResponseUtils.createErrorResponse(
					response.getErrorMessage());
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
    }
	
	@GetMapping("/{id}/photo")
	public void downloadFile(@PathVariable Integer id, HttpServletResponse httpResponse) 
			throws IOException 
	{
		GetCustomerPhotoUseCase useCase = new GetCustomerPhotoUseCase(
				RepositoryFactory.getCustomerRepository());
		
		GetCustomerPhotoRequest request = new GetCustomerPhotoRequest();
		
		request.setRequestingUser("admin");
		request.setCustomerId(id);
		
		GetCustomerPhotoResponse response = useCase.execute(request);
		
		if (response.hasError()) {
			if (ErrorResponseUtils.isAuthorizationError(response.getErrorMessage()))
				httpResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
			else
				httpResponse.setStatus(HttpStatus.NOT_FOUND.value());
			
			return;
		}
		
		httpResponse.setContentType(response.getMimeType());
		httpResponse.getOutputStream().write(response.getContent());
	}
}
