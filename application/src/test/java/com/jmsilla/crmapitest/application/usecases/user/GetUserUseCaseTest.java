package com.jmsilla.crmapitest.application.usecases.user;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import org.junit.*;

import com.jmsilla.crmapitest.application.dtos.user.*;
import com.jmsilla.crmapitest.domain.entities.User;
import com.jmsilla.crmapitest.testrepositories.TestUserRepository;

public class GetUserUseCaseTest {
	private TestUserRepository repository;
	
	@Before
	public void setUp() {
		repository = new TestUserRepository();
		repository.create(User.createAdminUser(1, "admin"));
		repository.create(User.createUser(2, "user"));
	}
	
	@Test
	public void onlyAdminUsersCanGetUsers() {
		GetUserRequest request = new GetUserRequest();
		
		request.setRequestingUser("user");
		request.setUserId(2);
		
		GetUserUseCase useCase = new GetUserUseCase(repository);
		
		GetUserResponse response = useCase.execute(request);
		
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
		GetUserRequest request = new GetUserRequest();
		
		request.setRequestingUser("admin");
		request.setUserId(3);
		
		GetUserUseCase useCase = new GetUserUseCase(repository);
		
		GetUserResponse response = useCase.execute(request);
		
		assertThat(response.hasError(), is(true));
		assertThat(response.getErrorMessage(), 
				is(equalTo("user.doesntExists")));
	}
	
	@Test
	public void mustReturnIdAndNameWhenUserWithIdExist() {
		GetUserRequest request = new GetUserRequest();
		
		request.setRequestingUser("admin");
		request.setUserId(2);
		
		GetUserUseCase useCase = new GetUserUseCase(repository);
		
		GetUserResponse response = useCase.execute(request);
		
		assertThat(response.hasError(), is(false));
		assertThat(response.getUserId(), is(equalTo(2)));
		assertThat(response.getUserName(), is(equalTo("user")));
		assertThat(repository.getLastCall(), is(equalTo("findById(2)")));
	}
}
