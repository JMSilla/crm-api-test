package com.jmsilla.crmapitest.api.utils;

import com.jmsilla.crmapitest.api.resources.UserResourceWithId;
import com.jmsilla.crmapitest.application.dtos.user.*;

public class UserMappers {
	public static UserResourceWithId mapToUserResource(GetUserResponse u) {
		UserResourceWithId resource = new UserResourceWithId();
		
		resource.setId(u.getUserId());
		resource.setName(u.getUserName());
		resource.setAdmin(u.getIsAdmin());
		
		return resource;
	}
	
	public static UserResourceWithId mapToUserResource(CreateUserRequest request, 
			CreateUserResponse response) 
	{
		UserResourceWithId resource = new UserResourceWithId();
		
		resource.setId(response.getCreatedUserId());
		resource.setName(request.getName());
		
		resource.setAdmin(false);
		
		if (request.getIsAdmin() != null)
			resource.setAdmin(request.getIsAdmin());
		
		return resource;
	}
	
	public static UserResourceWithId mapToUserResourceWithId(UpdateUserResponse response) {
		UserResourceWithId userResponse = new UserResourceWithId();
		
		userResponse.setId(response.getUpdatedUserId());
		userResponse.setName(response.getName());
		userResponse.setAdmin(response.getAdmin());
		return userResponse;
	}
}
