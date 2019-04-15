package com.jmsilla.crmapitest.application.usecases.customer;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import org.junit.*;

import com.jmsilla.crmapitest.application.dtos.customer.*;
import com.jmsilla.crmapitest.domain.entities.*;
import com.jmsilla.crmapitest.testrepositories.*;

public class GetCustomerDetailsUseCaseTest {
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
	public void shouldReturnErrorWhenNotExistsCustomerWithId() {
		GetCustomerDetailsRequest request = new GetCustomerDetailsRequest();
		
		request.setId(4);
		
		GetCustomerDetailsUseCase useCase = new GetCustomerDetailsUseCase(
				customerRepository);
		
		GetCustomerDetailsResponse response = useCase.execute(request);
		
		assertThat(response.hasError(), is(true));
		assertThat(response.getErrorMessage(), is((equalTo("customer.doesntExists"))));
	}
	
	@Test
	public void shouldReturnCorrectDataWhenCustomerExists() {
		GetCustomerDetailsRequest request = new GetCustomerDetailsRequest();
		
		request.setId(1);
		
		GetCustomerDetailsUseCase useCase = new GetCustomerDetailsUseCase(
				customerRepository);
		
		GetCustomerDetailsResponse response = useCase.execute(request);
		
		assertThat(response.hasError(), is(false));
		assertThat(response.getId(), is(equalTo(1)));
		assertThat(response.getName(), is(equalTo("Customer 1")));
		assertThat(response.getSurname(), is(equalTo("Surname 1")));
		assertThat(response.getCreatedByUsername(), is(equalTo("user")));
		assertThat(response.getModifiedByUsername(), is(nullValue()));
	}
}
