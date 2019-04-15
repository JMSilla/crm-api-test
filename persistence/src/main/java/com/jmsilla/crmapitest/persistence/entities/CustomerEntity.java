package com.jmsilla.crmapitest.persistence.entities;

import javax.persistence.*;

@Entity
@Table(name = "customer", schema = "public")
public class CustomerEntity {
	@Id
	private Integer id;
	
	@Column(length = 30, nullable = false)
	private String name;
	
	@Column(length = 30, nullable = false)
	private String surname;
	
	@Lob
	private byte[] photo;
	
	@Column (length = 100)
	private String photoMimeType;
	
	@ManyToOne(optional = false)
	private UserEntity createdByUser;
	
	@ManyToOne
	private UserEntity modifiedByUser;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public String getPhotoMimeType() {
		return photoMimeType;
	}

	public void setPhotoMimeType(String photoMimeType) {
		this.photoMimeType = photoMimeType;
	}

	public UserEntity getCreatedByUser() {
		return createdByUser;
	}

	public void setCreatedByUser(UserEntity createdByUser) {
		this.createdByUser = createdByUser;
	}

	public UserEntity getModifiedByUser() {
		return modifiedByUser;
	}

	public void setModifiedByUser(UserEntity modifiedByUser) {
		this.modifiedByUser = modifiedByUser;
	}
}
