package com.jmsilla.crmapitest.api.utils;

import com.jmsilla.crmapitest.api.resources.*;
import com.jmsilla.crmapitest.application.dtos.customer.*;

public class CustomerMappers {
	public static CustomerResourceWithId mapToCustomerWithIdResource(
			CustomerResponse response)
	{
		CustomerResourceWithId resource = new CustomerResourceWithId();
		
		resource.setId(response.getId());
		resource.setName(response.getName());
		resource.setSurname(response.getSurname());
		
		return resource;
	}
	
	public static CustomerDetailsResource mapToCustomerDetailsResource(
			GetCustomerDetailsResponse response)
	{
		CustomerDetailsResource resource = new CustomerDetailsResource();
		
		resource.setId(response.getId());
		resource.setName(response.getName());
		resource.setSurname(response.getSurname());
		resource.setCreatedBy(response.getCreatedByUsername());
		resource.setUpdatedBy(response.getModifiedByUsername());
		resource.setPhotoUrl(System.getenv("SERVER_SERVLET_CONTEXT_PATH")
				+ "/customers/" + response.getId() + "/photo");
		
		return resource;
	}
	
	public static CustomerResourceWithId mapToCustomerWithIdResource(
			CreateCustomerRequest request, CreateCustomerResponse response)
	{
		CustomerResourceWithId resource = new CustomerResourceWithId();
		
		resource.setId(response.getCreatedCustomerId());
		resource.setName(request.getName());
		resource.setSurname(request.getSurname());
		
		return resource;
	}
	
	public static CustomerResourceWithId mapToCustomerWithIdResource(
			UpdateCustomerResponse response)
	{
		CustomerResourceWithId resource = new CustomerResourceWithId();
		
		resource.setId(response.getUpdatedCustomerId());
		resource.setName(response.getName());
		resource.setSurname(response.getSurname());
		
		return resource;
	}
}
