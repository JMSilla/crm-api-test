package com.jmsilla.crmapitest.application.usecases.customer;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import org.junit.*;

import com.jmsilla.crmapitest.application.dtos.customer.*;
import com.jmsilla.crmapitest.domain.entities.*;
import com.jmsilla.crmapitest.testrepositories.*;

public class DeleteCustomerUseCaseTest {
	private TestCustomerRepository customerRepository; 
	
	@Before
	public void setUp() {
		User creatorUser = User.createUser(2, "user");
		
		customerRepository = new TestCustomerRepository();
		customerRepository.create(Customer.create(1, "Customer 1", "Surname 1", 
				creatorUser));
		customerRepository.create(Customer.create(2, "Customer 2", "Surname 2", 
				creatorUser));
	}
	
	@Test
	public void shouldReturnErrorWhenUserWithIdDoesntExists() {
		DeleteCustomerRequest request = new DeleteCustomerRequest();
		
		request.setId(5);
		
		DeleteCustomerUseCase useCase = new DeleteCustomerUseCase(customerRepository);
		
		DeleteCustomerResponse response = useCase.execute(request);
		
		assertThat(response.hasError(), is(true));
		assertThat(response.getErrorMessage(), is(equalTo("customer.doesntExists")));
	}
	
	@Test
	public void shouldDeleteCustomerWhenExists() {
		DeleteCustomerRequest request = new DeleteCustomerRequest();
		
		request.setId(1);
		
		DeleteCustomerUseCase useCase = new DeleteCustomerUseCase(
				customerRepository);
		
		DeleteCustomerResponse response = useCase.execute(request);
		
		assertThat(response.hasError(), is(false));
		assertThat(response.getDeletedCustomerId(), is(equalTo(1)));
		assertThat(customerRepository.getLastCall(), is(equalTo("delete(1)")));
	}
}
