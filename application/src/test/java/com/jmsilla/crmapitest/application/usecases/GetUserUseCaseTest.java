package com.jmsilla.crmapitest.application.usecases;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import org.junit.*;

import com.jmsilla.crmapitest.application.dtos.*;
import com.jmsilla.crmapitest.domain.entities.User;
import com.jmsilla.crmapitest.testrepositories.TestUserRepository;

public class GetUserUseCaseTest {
private TestUserRepository repository = new TestUserRepository();
	
	@Before
	public void setUp() {
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
		
		assertThat(response.getError(), is(true));
		assertThat(response.getErrorMessage(), 
				is(equalTo("error.authorization")));
		
		request.setRequestingUser(null);
		
		response = useCase.execute(request);
		
		assertThat(response.getError(), is(true));
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
		
		assertThat(response.getError(), is(true));
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
		
		assertThat(response.getError(), is(false));
		assertThat(response.getUserId(), is(equalTo(2)));
		assertThat(response.getUserName(), is(equalTo("user")));
	}
}
