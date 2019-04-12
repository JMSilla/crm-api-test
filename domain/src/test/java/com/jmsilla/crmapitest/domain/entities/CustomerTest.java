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
		customer = Customer.create(1, "name1", "surname1");
	}
	
	@Test
	public void customerNameMustHaveNewValueWhenChanged() {
		customer.changeName("name2");
		
		assertThat(customer.getName(), is(equalTo("name2")));
	}
	
	@Test
	public void customerNameCanNotBeNullWhenChanged() {
		expectedEx.expect(RequiredFieldException.class);
		expectedEx.expectMessage("name");
		
		customer.changeName(null);
	}
	
	@Test
	public void customerNameCannotBeAnEmptyStringWhenChanged() {
		expectedEx.expect(RequiredFieldException.class);
		expectedEx.expectMessage("name");
		
		customer.changeName("  ");
	}
	
	@Test
	public void customerNameMustNotExceedMaxLengthWhenChanged() {
		expectedEx.expect(LengthExceededException.class);
		expectedEx.expectMessage("name");

		customer.changeName(StringTestUtils.generateStringOfLength(40));
	}
	
	@Test
	public void customerSurnameMustHaveNewValueWhenChangedWhenChanged() {
		customer.changeSurname("surname2");
		
		assertThat(customer.getSurname(), is(equalTo("surname2")));
	}
	
	@Test
	public void customerSurnameCanNotBeNullWhenChanged() {
		expectedEx.expect(RequiredFieldException.class);
		expectedEx.expectMessage("surname");
		
		customer.changeSurname(null);
	}
	
	@Test
	public void customerSurnameCannotBeAnEmptyStringWhenChanged() {
		expectedEx.expect(RequiredFieldException.class);
		expectedEx.expectMessage("surname");
		
		customer.changeSurname("\t");
	}
	
	@Test
	public void customerSurnameMustNotExceedMaxLengthWhenChanged() {
		expectedEx.expect(LengthExceededException.class);
		expectedEx.expectMessage("surname");

		customer.changeSurname(StringTestUtils.generateStringOfLength(40));
	}
	
	@Test
	public void customerPhotoMustChange() {
		Image image = new Image(ImageTestUtils.createSampleImageBytes());
		
		customer.changePhoto(image);
		
		assertThat(customer.getPhoto(), is(equalTo(image)));
	}
}
