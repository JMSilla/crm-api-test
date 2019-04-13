package com.jmsilla.crmapitest.domain.entities;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import org.junit.*;
import org.junit.rules.ExpectedException;

import com.jmsilla.crmapitest.domain.exceptions.*;
import com.jmsilla.crmapitest.domain.utils.*;

public class UserCreationTest {
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	@Test
	public void userShouldBeCorrectyCreated() {
		User user = User.createUser(1, "user");
		
		assertThat(user.getId(), is(equalTo(1)));
		assertThat(user.getName(), is(equalTo("user")));
		assertThat(user.isAdmin(), is(false));
	}
	
	@Test
	public void adminUserShouldBeCorrectyCreated() {
		User user = User.createAdminUser(1, "user");
		
		assertThat(user.getId(), is(equalTo(1)));
		assertThat(user.getName(), is(equalTo("user")));
		assertThat(user.isAdmin(), is(true));
	}
	
	@Test
	public void userMustHaveAId() {
		expectedEx.expect(RequiredFieldException.class);
		expectedEx.expectMessage(equalTo("id.requiredField"));
		
		User.createUser(null, "user");
	}
	
	@Test
	public void userMustHaveAPositiveId() {
		expectedEx.expect(RequiredFieldException.class);
		expectedEx.expectMessage(equalTo("id.requiredField"));
		
		User.createUser(-1, "user");
	}
	
	@Test
	public void userMustHaveAName() {
		expectedEx.expect(RequiredFieldException.class);
		expectedEx.expectMessage(equalTo("name.requiredField"));
		
		User.createUser(1, null);
	}
	
	@Test
	public void userNameCannotBeAnEmptyString() {
		expectedEx.expect(RequiredFieldException.class);
		expectedEx.expectMessage(equalTo("name.requiredField"));
		
		User.createUser(1, "");
	}
	
	@Test
	public void userNameMustNotExceedMaxLength() {
		expectedEx.expect(LengthExceededException.class);
		expectedEx.expectMessage(equalTo("name.lengthExceeded"));
		
		User.createUser(1, StringTestUtils.generateStringOfLength(31));
	}
}
