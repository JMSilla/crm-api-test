package com.jmsilla.crmapitest.application.usecases.customer;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import org.junit.*;

import com.jmsilla.crmapitest.application.dtos.customer.*;
import com.jmsilla.crmapitest.domain.entities.*;
import com.jmsilla.crmapitest.testrepositories.*;

public class CreateCustomerUseCaseTest {
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
	public void customerMustBeCorrectlyCreated() {
		CreateCustomerRequest request = new CreateCustomerRequest();
		
		request.setRequestingUser("user");
		request.setName("First Name");
		request.setSurname("Customer Surname");
		
		CreateCustomerUseCase useCase = new CreateCustomerUseCase(userRepository,
				customerRepository);
		
		CreateCustomerResponse response = useCase.execute(request);
		
		assertThat(response.getCreatedCustomerId(), is(equalTo(3)));
		assertThat(customerRepository.getLastCall(), is(equalTo("create(3)")));
	}
}
