package com.jmsilla.crmapitest.api.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.jmsilla.crmapitest.api.resources.*;
import com.jmsilla.crmapitest.api.utils.*;
import com.jmsilla.crmapitest.application.dtos.customer.*;
import com.jmsilla.crmapitest.application.usecases.customer.*;

@RestController
@RequestMapping(value = "/customers", headers = "Accept=application/json")
public class CustomerController {
	@GetMapping
	public ResponseEntity<Object> getAllCustomers() {
		ListCustomersUseCase useCase = new ListCustomersUseCase(
				RepositoryFactory.getCustomerRepository());
		
		ListCustomersResponse response = useCase.execute();
		
		if (response.hasError()) {
			return ErrorResponseUtils.createErrorResponse(
					response.getErrorMessage());
		}
		
		List<CustomerResourceWithId> resources = response.getCustomers().stream()
				.map(CustomerMappers::mapToCustomerWithIdResource)
				.collect(Collectors.toList());
		
		return new ResponseEntity<>(resources, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getCustomer(@PathVariable Integer id) {
		GetCustomerDetailsUseCase useCase = new GetCustomerDetailsUseCase(
				RepositoryFactory.getCustomerRepository());
		
		GetCustomerDetailsRequest request = new GetCustomerDetailsRequest();
		
		request.setId(id);
		
		GetCustomerDetailsResponse response = useCase.execute(request);
		
		if (response.hasError()) {
			return ErrorResponseUtils.createErrorResponse(
					response.getErrorMessage(), id);
		}
		
		return new ResponseEntity<>(CustomerMappers.mapToCustomerDetailsResource(
				response), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Object> createCustomer(
			@RequestBody CustomerResource createCustomerResource) 
	{
		CreateCustomerUseCase useCase = new CreateCustomerUseCase(
				RepositoryFactory.getUserRepository(), 
				RepositoryFactory.getCustomerRepository());

		CreateCustomerRequest request = new CreateCustomerRequest();
		
		request.setRequestingUser("admin");
		request.setName(createCustomerResource.getName());
		request.setSurname(createCustomerResource.getSurname());

		CreateCustomerResponse response = useCase.execute(request);

		if (response.hasError()) {
			return ErrorResponseUtils.createErrorResponse(
					response.getErrorMessage());
		}
		
		return new ResponseEntity<>(CustomerMappers.mapToCustomerWithIdResource(
				request, response), HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateCustomer(@PathVariable Integer id,
			@RequestBody CustomerResource updateCustomerResource)
	{
		UpdateCustomerUseCase useCase = new UpdateCustomerUseCase(
				RepositoryFactory.getCustomerRepository(), 
				RepositoryFactory.getUserRepository());

		UpdateCustomerRequest request = new UpdateCustomerRequest();
		
		request.setRequestingUser("admin");
		request.setId(id);
		request.setName(updateCustomerResource.getName());
		request.setSurname(updateCustomerResource.getSurname());

		UpdateCustomerResponse response = useCase.execute(request);
		
		if (response.hasError()) {
			return ErrorResponseUtils.createErrorResponse(
					response.getErrorMessage(), id);
		}
		
		return new ResponseEntity<>(CustomerMappers.mapToCustomerWithIdResource(
				response), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteCustomer(@PathVariable Integer id) {
		DeleteCustomerUseCase useCase = new DeleteCustomerUseCase(
				RepositoryFactory.getCustomerRepository());
		
		DeleteCustomerRequest request = new DeleteCustomerRequest();
		
		request.setId(id);
		
		DeleteCustomerResponse response = useCase.execute(request);
		
		if (response.hasError()) {
			return ErrorResponseUtils.createErrorResponse(
					response.getErrorMessage(), id);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
