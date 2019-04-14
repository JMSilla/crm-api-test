package com.jmsilla.crmapitest.application.usecases;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.*;

import com.jmsilla.crmapitest.application.dtos.*;
import com.jmsilla.crmapitest.domain.entities.User;
import com.jmsilla.crmapitest.testrepositories.TestUserRepository;

public class ListUsersUseCaseTest {
	private TestUserRepository repository;
	
	@Before
	public void setUp() {
		repository = new TestUserRepository();
		repository.create(User.createAdminUser(1, "admin"));
		repository.create(User.createUser(2, "user"));
	}
	
	@Test
	public void onlyAdminUsersCanGetUserList() {
		ListUsersRequest request = new ListUsersRequest();
		
		request.setRequestingUser("user");
		
		ListUsersUseCase useCase = new ListUsersUseCase(repository);
		
		ListUsersResponse response = useCase.execute(request);
		
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
	public void mustReturnUserList() {
		ListUsersRequest request = new ListUsersRequest();
		
		request.setRequestingUser("admin");
		
		ListUsersUseCase useCase = new ListUsersUseCase(repository);
		
		ListUsersResponse response = useCase.execute(request);
		
		assertThat(repository.getLastCall(), is(equalTo("findAll()")));
		
		List<Integer> allResponseUserIds = response.getUsers().stream()
				.map(user -> user.getUserId())
				.collect(Collectors.toList());
		
		assertThat(allResponseUserIds, containsInAnyOrder(1, 2));
	}
}
