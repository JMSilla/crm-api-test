package com.jmsilla.crmapitest.application.usecases.customer;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import org.junit.*;

import com.jmsilla.crmapitest.application.dtos.customer.*;
import com.jmsilla.crmapitest.domain.entities.*;
import com.jmsilla.crmapitest.testrepositories.*;

public class UpdateCustomerUseCaseTest {
	private TestUserRepository userRepository;
	private TestCustomerRepository customerRepository; 
	
	@Before
	public void setUp() {
		User creatorUser = User.createUser(2, "user");
		
		userRepository = new TestUserRepository();
		userRepository.create(User.createAdminUser(1, "admin"));
		userRepository.create(creatorUser);
		
		customerRepository = new TestCustomerRepository();
		customerRepository.create(Customer.create(1, "Customer 1", "Surname 1", 
				creatorUser));
		customerRepository.create(Customer.create(2, "Customer 2", "Surname 2", 
				creatorUser));
	}
	
	@Test
	public void shouldReturnErrorWhenUserWithIdDoesntExists() {
		UpdateCustomerRequest request = new UpdateCustomerRequest();
		
		request.setId(9);
		request.setName("First Name");
		request.setSurname("Customer Surname");
		request.setRequestingUser("user");
		
		UpdateCustomerUseCase useCase = new UpdateCustomerUseCase(
				customerRepository, userRepository);
		
		UpdateCustomerResponse response = useCase.execute(request);
		
		assertThat(response.hasError(), is(true));
		assertThat(response.getErrorMessage(), is(equalTo("customer.doesntExists")));
	}
	
	@Test
	public void shouldUpdateCustomerWhenExists() {
		UpdateCustomerRequest request = new UpdateCustomerRequest();
		
		request.setId(1);
		request.setName("First Name");
		request.setSurname("Customer Surname");
		request.setRequestingUser("admin");
		
		UpdateCustomerUseCase useCase = new UpdateCustomerUseCase(
				customerRepository, userRepository);
		
		UpdateCustomerResponse response = useCase.execute(request);
		
		assertThat(response.hasError(), is(false));
		assertThat(response.getUpdatedCustomerId(), is(equalTo(1)));
		assertThat(customerRepository.getLastCall(), is(equalTo("update(1)")));
	}
}
