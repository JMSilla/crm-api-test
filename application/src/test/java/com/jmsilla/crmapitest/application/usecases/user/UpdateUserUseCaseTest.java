package com.jmsilla.crmapitest.application.usecases.user;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import org.junit.*;

import com.jmsilla.crmapitest.application.dtos.user.*;
import com.jmsilla.crmapitest.domain.entities.User;
import com.jmsilla.crmapitest.testrepositories.TestUserRepository;

public class UpdateUserUseCaseTest {
	private TestUserRepository repository;
	
	@Before
	public void setUp() {
		repository = new TestUserRepository();
		repository.create(User.createAdminUser(1, "admin"));
		repository.create(User.createUser(2, "user"));
	}
	
	@Test
	public void onlyAdminUsersCanUpdateUsers() {
		UpdateUserRequest request = new UpdateUserRequest();
		
		request.setRequestingUser("user");
		request.setId(1);
		request.setName("newuser");
		request.setIsAdmin(false);
		
		UpdateUserUseCase useCase = new UpdateUserUseCase(repository);
		
		UpdateUserResponse response = useCase.execute(request);
		
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
	public void usernameMustBeUnique() {
		UpdateUserRequest request = new UpdateUserRequest();
		
		request.setRequestingUser("admin");
		request.setId(2);
		request.setName("admin");
		request.setIsAdmin(false);
		
		UpdateUserUseCase useCase = new UpdateUserUseCase(repository);
		
		UpdateUserResponse response = useCase.execute(request);
		
		assertThat(response.hasError(), is(true));
		assertThat(response.getErrorMessage(), 
				is(equalTo("user.existsWithSameName")));
	}
	
	@Test
	public void userMustBeCorrectlyUpdated() {
		UpdateUserRequest request = new UpdateUserRequest();
		
		request.setRequestingUser("admin");
		request.setId(2);
		request.setName("newuser");
		request.setIsAdmin(false);
		
		UpdateUserUseCase useCase = new UpdateUserUseCase(repository);
		
		UpdateUserResponse response = useCase.execute(request);
		
		assertThat(response.hasError(), is(false));
		assertThat(response.getUpdatedUserId(), is(notNullValue()));
		assertThat(repository.getLastCall(), is(equalTo("update(2)")));
	}
}
