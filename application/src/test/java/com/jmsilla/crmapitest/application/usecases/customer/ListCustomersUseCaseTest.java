package com.jmsilla.crmapitest.application.usecases.customer;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.util.stream.Collectors;

import org.junit.Test;

import com.jmsilla.crmapitest.application.dtos.customer.*;
import com.jmsilla.crmapitest.domain.entities.*;
import com.jmsilla.crmapitest.testrepositories.TestCustomerRepository;

public class ListCustomersUseCaseTest {
	private TestCustomerRepository repository;
	
	@Test
	public void shouldReturnAndEmptyListWhenNoCustomersInRepository() {
		repository = new TestCustomerRepository();
		
		ListCustomersUseCase useCase = new ListCustomersUseCase(repository);
		
		ListCustomersResponse response = useCase.execute();
		
		assertThat(response.getCustomers(), is(empty()));
	}
	
	@Test
	public void shouldReturnAllCustomersInRepository() {
		repository = new TestCustomerRepository();
		
		User user = User.createUser(1, "user");
		
		repository.create(Customer.create(1, "c1", "s1", user));
		repository.create(Customer.create(2, "c2", "s2", user));
		
		ListCustomersUseCase useCase = new ListCustomersUseCase(repository);
		
		ListCustomersResponse response = useCase.execute();
		
		assertThat(response.getCustomers().stream()
				.map(c -> c.getId()).collect(Collectors.toList()), 
				containsInAnyOrder(1, 2));
	}
}
