package com.jmsilla.crmapitest.application.dtos.customer;

import com.jmsilla.crmapitest.application.dtos.BaseRequest;

public class ChangeCustomerPhotoRequest extends BaseRequest {
	private Integer customerId;
	private String mimeType;
	private byte[] photoContent;
	
	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public byte[] getPhotoContent() {
		return photoContent;
	}

	public void setPhotoContent(byte[] photoContent) {
		this.photoContent = photoContent;
	}
}
