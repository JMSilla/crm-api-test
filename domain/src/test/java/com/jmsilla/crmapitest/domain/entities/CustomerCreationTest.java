package com.jmsilla.crmapitest.domain.entities;

import org.junit.*;
import org.junit.rules.ExpectedException;

import com.jmsilla.crmapitest.domain.exceptions.*;
import com.jmsilla.crmapitest.domain.utils.StringTestUtils;

public class CustomerCreationTest {
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	@Test
	public void customerShouldHaveAnId() {
		expectedEx.expect(RequiredFieldException.class);
		expectedEx.expectMessage("id");
		
		Customer.create(null, "name", "surname");
	}
	
	@Test
	public void customerShouldHaveAnIdGreaterThan0() {
		expectedEx.expect(RequiredFieldException.class);
		expectedEx.expectMessage("id");
		
		Customer.create(-1, "name", "surname");
	}
	
	@Test
	public void customerShouldHaveAName() {
		expectedEx.expect(RequiredFieldException.class);
		expectedEx.expectMessage("name");
		
		Customer.create(1, null, "surname");
	}
	
	@Test
	public void customerNameCannotBeAnEmptyString() {
		expectedEx.expect(RequiredFieldException.class);
		expectedEx.expectMessage("name");
		
		Customer.create(1, "", "surname");
	}
	
	@Test
	public void customerNameMustNotExceedMaxLength() {
		expectedEx.expect(LengthExceededException.class);
		expectedEx.expectMessage("name");
		
		Customer.create(1, StringTestUtils.generateStringOfLength(31), 
				"surname");
	}
	
	@Test
	public void customerShouldHaveASurname() {
		expectedEx.expect(RequiredFieldException.class);
		expectedEx.expectMessage("surname");
		
		Customer.create(1, "name", null);
	}
	
	@Test
	public void customerSurnameCannotBeAnEmptyString() {
		expectedEx.expect(RequiredFieldException.class);
		expectedEx.expectMessage("surname");
		
		Customer.create(1, "name", "");
	}
	
	@Test
	public void customerSurnameMustNotExceedMaxLength() {
		expectedEx.expect(LengthExceededException.class);
		expectedEx.expectMessage("surname");
		
		Customer.create(1, "name", StringTestUtils.generateStringOfLength(31));
	}
}
