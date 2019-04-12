package com.jmsilla.crmapitest.domain.valueobjects;

import java.util.Arrays;

import com.jmsilla.crmapitest.domain.exceptions.RequiredFieldException;

public class Image {
	private String mimeType;
	private byte[] imageContent;

	public Image(byte[] imageContent) {
		if (imageContent == null || imageContent.length == 0)
			throw new RequiredFieldException("imageContent");

		this.mimeType = "*";
		this.imageContent = imageContent;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null || !(obj instanceof Image))
			return false;
		
		Image other = (Image) obj;
		
		if (!Arrays.equals(imageContent, other.imageContent))
			return false;
		
		if (mimeType == null) {
			if (other.mimeType != null)
				return false;
		} else if (!mimeType.equals(other.mimeType)) {
			return false;
		}
		
		return true;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void changeMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public byte[] getImageContent() {
		return imageContent;
	}
}
