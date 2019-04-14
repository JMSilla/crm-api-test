package com.jmsilla.crmapitest.application.usecases;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import org.junit.*;

import com.jmsilla.crmapitest.application.dtos.*;
import com.jmsilla.crmapitest.domain.entities.User;
import com.jmsilla.crmapitest.testrepositories.TestUserRepository;

public class DeleteUserUseCaseTest {
	private TestUserRepository repository;
	
	@Before
	public void setUp() {
		repository = new TestUserRepository();
		repository.create(User.createAdminUser(1, "admin"));
		repository.create(User.createUser(2, "user"));
	}
	
	@Test
	public void onlyAdminUsersCanDeleteUsers() {
		DeleteUserRequest request = new DeleteUserRequest();
		
		request.setRequestingUser("user");
		request.setUserId(2);
		
		DeleteUserUseCase useCase = new DeleteUserUseCase(repository);
		
		DeleteUserResponse response = useCase.execute(request);
		
		assertThat(response.hasError(), is(true));
		assertThat(response.getErrorMessage(), 
				is(equalTo("error.authorization")));
		
		request.setRequestingUser(null);
		
		response = useCase.execute(request);
		
		assertThat(response.hasError(), is(true));
		assertThat(response.getErrorMessage(), 
				is(equalTo("error.authorization")));
	}
	
	@Test
	public void mustReturnErrorWhenUserWithIdDoesntExist() {
		DeleteUserRequest request = new DeleteUserRequest();
		
		request.setRequestingUser("admin");
		request.setUserId(3);
		
		DeleteUserUseCase useCase = new DeleteUserUseCase(repository);
		
		DeleteUserResponse response = useCase.execute(request);
		
		assertThat(response.hasError(), is(true));
		assertThat(response.getErrorMessage(), 
				is(equalTo("user.doesntExists")));
	}
	
	@Test
	public void mustReturnIdAndNameWhenUserWithIdExist() {
		DeleteUserRequest request = new DeleteUserRequest();
		
		request.setRequestingUser("admin");
		request.setUserId(2);
		
		DeleteUserUseCase useCase = new DeleteUserUseCase(repository);
		
		DeleteUserResponse response = useCase.execute(request);
		
		assertThat(response.hasError(), is(false));
		assertThat(response.getDeletedUserId(), is(equalTo(2)));
		assertThat(repository.getLastCall(), is(equalTo("delete(2)")));
	}
}
