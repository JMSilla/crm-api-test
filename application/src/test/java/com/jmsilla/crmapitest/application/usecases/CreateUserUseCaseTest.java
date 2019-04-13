package com.jmsilla.crmapitest.application.usecases;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import org.junit.*;

import com.jmsilla.crmapitest.application.dtos.*;
import com.jmsilla.crmapitest.domain.entities.User;
import com.jmsilla.crmapitest.testrepositories.TestUserRepository;

public class CreateUserUseCaseTest {
	private TestUserRepository repository = new TestUserRepository();
	
	@Before
	public void setUp() {
		repository.create(User.createAdminUser(1, "admin"));
		repository.create(User.createUser(2, "user"));
	}
	
	@Test
	public void onlyAdminUsersCanCreateUsers() {
		CreateUserRequest request = new CreateUserRequest();
		
		request.setRequestingUser("user");
		request.setName("newuser");
		request.setIsAdmin(false);
		
		CreateUserUseCase useCase = new CreateUserUseCase(repository);
		
		CreateUserResponse response = useCase.execute(request);
		
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
	public void usernameMustBeUnique() {
		CreateUserRequest request = new CreateUserRequest();
		
		request.setRequestingUser("admin");
		request.setName("user");
		request.setIsAdmin(false);
		
		CreateUserUseCase useCase = new CreateUserUseCase(repository);
		
		CreateUserResponse response = useCase.execute(request);
		
		assertThat(response.getError(), is(true));
		assertThat(response.getErrorMessage(), 
				is(equalTo("user.existsWithSameName")));
	}
	
	@Test
	public void userMustBeCorrectlyCreated() {
		CreateUserRequest request = new CreateUserRequest();
		
		request.setRequestingUser("admin");
		request.setName("newuser");
		request.setIsAdmin(false);
		
		CreateUserUseCase useCase = new CreateUserUseCase(repository);
		
		CreateUserResponse response = useCase.execute(request);
		
		assertThat(response.getError(), is(false));
		assertThat(response.getCreatedUserId(), is(notNullValue()));
	}
	
	@Test
	public void adminUserMustBeCorrectlyCreated() {
		CreateUserRequest request = new CreateUserRequest();
		
		request.setRequestingUser("admin");
		request.setName("newadminuser");
		request.setIsAdmin(true);
		
		CreateUserUseCase useCase = new CreateUserUseCase(repository);
		
		CreateUserResponse response = useCase.execute(request);
		
		assertThat(response.getError(), is(false));
		assertThat(response.getCreatedUserId(), is(notNullValue()));
	}
}
