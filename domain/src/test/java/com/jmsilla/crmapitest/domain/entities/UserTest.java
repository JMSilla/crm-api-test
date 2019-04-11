package com.jmsilla.crmapitest.domain.entities;

import org.junit.*;
import org.junit.rules.ExpectedException;

import com.jmsilla.crmapitest.domain.exceptions.*;
import com.jmsilla.crmapitest.utils.StringTestUtils;

public class UserTest {
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	@Test
	public void userMustHaveAName() {
		expectedEx.expect(RequiredFieldException.class);
		expectedEx.expectMessage("name");
		
		new User(null, null);
	}
	
	@Test
	public void userCannotBeAnEmptyString() {
		expectedEx.expect(RequiredFieldException.class);
		expectedEx.expectMessage("name");
		
		new User("", null);
	}
	
	@Test
	public void userNameMustNotExceedMaxLength() {
		expectedEx.expect(LengthExceededException.class);
		expectedEx.expectMessage("name");
		
		new User(StringTestUtils.generateStringOfLength(31), null);
	}
}
