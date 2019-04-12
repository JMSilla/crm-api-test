package com.jmsilla.crmapitest.domain.valueobjects;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import org.junit.*;
import org.junit.rules.ExpectedException;

import com.jmsilla.crmapitest.domain.exceptions.RequiredFieldException;
import com.jmsilla.crmapitest.domain.utils.ImageTestUtils;

public class ImageTest {
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	@Test
	public void imageShouldContainByteContent() {
		expectedEx.expect(RequiredFieldException.class);
		expectedEx.expectMessage("imageContent");
		
		new Image(null);
	}
	
	@Test
	public void imageShouldContainNonEmptyByteContent() {
		expectedEx.expect(RequiredFieldException.class);
		expectedEx.expectMessage("imageContent");
		
		new Image(new byte[0]);
	}
	
	@Test
	public void imageShouldContainDefaultMimeTypeWhenCreated() {
		Image image = new Image(ImageTestUtils.createSampleImageBytes());
		
		assertThat(image.getMimeType(), is(equalTo("*")));
	}
	
	@Test
	public void imageMimeTypeCanBeChanged() {
		String newMimeType = "image/png";
		Image image = new Image(ImageTestUtils.createSampleImageBytes());
		
		image.changeMimeType(newMimeType);

		assertThat(image.getMimeType(), is(equalTo(newMimeType)));
	}
}
