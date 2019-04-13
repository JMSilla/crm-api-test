package com.jmsilla.crmapitest.domain.entities;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import org.junit.*;
import org.junit.rules.ExpectedException;

import com.jmsilla.crmapitest.domain.exceptions.*;
import com.jmsilla.crmapitest.domain.utils.*;
import com.jmsilla.crmapitest.domain.valueobjects.Image;

public class CustomerTest {
	private Customer customer;
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	@Before
	public void setUp() {
		customer = Customer.create(1, "name1", "surname1",
				UserTestUtils.testUser());
	}
	
	@Test
	public void customerNameMustHaveNewValueWhenChanged() {
		customer.changeName("name2", UserTestUtils.testUser());
		
		assertThat(customer.getName(), is(equalTo("name2")));
	}
	
	@Test
	public void customerNameCanNotBeNullWhenChanged() {
		expectedEx.expect(RequiredFieldException.class);
		expectedEx.expectMessage("name");
		
		customer.changeName(null, UserTestUtils.testUser());
	}
	
	@Test
	public void customerNameCannotBeAnEmptyStringWhenChanged() {
		expectedEx.expect(RequiredFieldException.class);
		expectedEx.expectMessage("name");
		
		customer.changeName("  ", UserTestUtils.testUser());
	}
	
	@Test
	public void customerNameMustNotExceedMaxLengthWhenChanged() {
		expectedEx.expect(LengthExceededException.class);
		expectedEx.expectMessage("name");

		customer.changeName(StringTestUtils.generateStringOfLength(40),
				UserTestUtils.testUser());
	}
	
	@Test
	public void customerSurnameMustHaveNewValueWhenChangedWhenChanged() {
		customer.changeSurname("surname2", UserTestUtils.testUser());
		
		assertThat(customer.getSurname(), is(equalTo("surname2")));
	}
	
	@Test
	public void customerSurnameCanNotBeNullWhenChanged() {
		expectedEx.expect(RequiredFieldException.class);
		expectedEx.expectMessage("surname");
		
		customer.changeSurname(null, UserTestUtils.testUser());
	}
	
	@Test
	public void customerSurnameCannotBeAnEmptyStringWhenChanged() {
		expectedEx.expect(RequiredFieldException.class);
		expectedEx.expectMessage("surname");
		
		customer.changeSurname("\t", UserTestUtils.testUser());
	}
	
	@Test
	public void customerSurnameMustNotExceedMaxLengthWhenChanged() {
		expectedEx.expect(LengthExceededException.class);
		expectedEx.expectMessage("surname");

		customer.changeSurname(StringTestUtils.generateStringOfLength(40),
				UserTestUtils.testUser());
	}
	
	@Test
	public void customerPhotoMustChange() {
		Image image = new Image(ImageTestUtils.createSampleImageBytes());
		
		customer.changePhoto(image, UserTestUtils.testUser());
		
		assertThat(customer.getPhoto(), is(equalTo(image)));
	}
	
	@Test
	public void modifierUserMustBeSetWhenChangingCustomerName() {
		expectedEx.expect(RequiredFieldException.class);
		expectedEx.expectMessage("modifiedByUsername");
		
		customer.changeName("name", null);
	}
	
	@Test
	public void modifierUserMustBeSetWhenChangingCustomerSurname() {
		expectedEx.expect(RequiredFieldException.class);
		expectedEx.expectMessage("modifiedByUsername");
		
		customer.changeSurname("surname", null);
	}
	
	@Test
	public void modifierUserMustBeSetWhenChangingCustomerPhoto() {
		expectedEx.expect(RequiredFieldException.class);
		expectedEx.expectMessage("modifiedByUsername");
		
		customer.changeSurname("surname", null);
	}
	
	@Test
	public void modifierUserMustBeChangeWhenChangingCustomer() {
		customer.changeName("newname", UserTestUtils.testUser("user1"));
		assertThat(customer.getModifiedByUsername(), is(equalTo("user1")));
		
		customer.changeSurname("newsurname", UserTestUtils.testUser("user2"));
		assertThat(customer.getModifiedByUsername(), is(equalTo("user2")));
		
		customer.changePhoto(new Image(ImageTestUtils.createSampleImageBytes()), 
				UserTestUtils.testUser("user3"));
		assertThat(customer.getModifiedByUsername(), is(equalTo("user3")));
	}
}
