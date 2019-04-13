package com.jmsilla.crmapitest.domain.entities;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import org.junit.*;
import org.junit.rules.ExpectedException;

import com.jmsilla.crmapitest.domain.exceptions.*;
import com.jmsilla.crmapitest.domain.utils.*;

public class UserTest {
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	@Test
	public void userNameCanBeChanged() {
		User user = UserTestUtils.testUser();
		
		user.changeName("newname");
		
		assertThat(user.getName(), is(equalTo("newname")));
	}
	
	@Test
	public void userMustHaveANameWhenChanged() {
		User user = UserTestUtils.testUser();
		
		expectedEx.expect(RequiredFieldException.class);
		expectedEx.expectMessage(equalTo("name.requiredField"));
		
		user.changeName(null);
	}
	
	@Test
	public void userNameCannotBeAnEmptyStringWhenChanged() {
		User user = UserTestUtils.testUser();
		
		expectedEx.expect(RequiredFieldException.class);
		expectedEx.expectMessage(equalTo("name.requiredField"));
		
		user.changeName("\n");
	}
	
	@Test
	public void userNameMustNotExceedMaxLengthWhenChanged() {
		User user = UserTestUtils.testUser();
		
		expectedEx.expect(LengthExceededException.class);
		expectedEx.expectMessage(equalTo("name.lengthExceeded"));
		
		user.changeName(StringTestUtils.generateStringOfLength(50));
	}
	
	@Test
	public void userAdminStateCanBeChanged() {
		User user = UserTestUtils.testUser();
		
		user.setAsAdmin();
		assertThat(user.isAdmin(), is(equalTo(true)));
		
		user.setAsNormalUser();
		assertThat(user.isAdmin(), is(equalTo(false)));
	}
}
