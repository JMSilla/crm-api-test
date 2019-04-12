package com.jmsilla.crmapitest.domain.entities;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import org.junit.*;
import org.junit.rules.ExpectedException;

import com.jmsilla.crmapitest.domain.exceptions.*;
import com.jmsilla.crmapitest.domain.utils.*;

public class CustomerCreationTest {
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	@Test
	public void customerShouldBeCorrectlyCreated() {
		User createdByUser = UserTestUtils.testUser();
		Customer customer = Customer.create(1, "name", "surname",
				createdByUser);
		
		assertThat(customer.getId(), is(equalTo(1)));
		assertThat(customer.getName(), is(equalTo("name")));
		assertThat(customer.getSurname(), is(equalTo("surname")));
		assertThat(customer.getCreatedByUsername(), is(equalTo(createdByUser.getName())));
	}
	
	@Test
	public void customerShouldHaveAnId() {
		expectedEx.expect(RequiredFieldException.class);
		expectedEx.expectMessage("id");
		
		Customer.create(null, "name", "surname",
				UserTestUtils.testUser());
	}
	
	@Test
	public void customerShouldHaveAnIdGreaterThan0() {
		expectedEx.expect(RequiredFieldException.class);
		expectedEx.expectMessage("id");
		
		Customer.create(-1, "name", "surname",
				UserTestUtils.testUser());
	}
	
	@Test
	public void customerShouldHaveAName() {
		expectedEx.expect(RequiredFieldException.class);
		expectedEx.expectMessage("name");
		
		Customer.create(1, null, "surname",
				UserTestUtils.testUser());
	}
	
	@Test
	public void customerNameCannotBeAnEmptyString() {
		expectedEx.expect(RequiredFieldException.class);
		expectedEx.expectMessage("name");
		
		Customer.create(1, "", "surname",
				UserTestUtils.testUser());
	}
	
	@Test
	public void customerNameMustNotExceedMaxLength() {
		expectedEx.expect(LengthExceededException.class);
		expectedEx.expectMessage("name");
		
		Customer.create(1, StringTestUtils.generateStringOfLength(31), 
				"surname", UserTestUtils.testUser());
	}
	
	@Test
	public void customerShouldHaveASurname() {
		expectedEx.expect(RequiredFieldException.class);
		expectedEx.expectMessage("surname");
		
		Customer.create(1, "name", null, UserTestUtils.testUser());
	}
	
	@Test
	public void customerSurnameCannotBeAnEmptyString() {
		expectedEx.expect(RequiredFieldException.class);
		expectedEx.expectMessage("surname");
		
		Customer.create(1, "name", "", UserTestUtils.testUser());
	}
	
	@Test
	public void customerSurnameMustNotExceedMaxLength() {
		expectedEx.expect(LengthExceededException.class);
		expectedEx.expectMessage("surname");
		
		Customer.create(1, "name", 
				StringTestUtils.generateStringOfLength(31),
				UserTestUtils.testUser());
	}
	
	@Test
	public void customerShouldHaveCreatedByUser() {
		expectedEx.expect(RequiredFieldException.class);
		expectedEx.expectMessage("createdByUsername");
		
		Customer.create(1, "name", "surname", null);
	}
}
