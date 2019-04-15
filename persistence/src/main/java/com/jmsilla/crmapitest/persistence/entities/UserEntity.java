package com.jmsilla.crmapitest.persistence.entities;

import javax.persistence.*;

@Entity
@Table(name = "user", schema = "public")
public class UserEntity {
	@Id
	private Integer id;
	
	@Column(length = 30, nullable = false)
	private String name;
	
	@Column(nullable = false)
	private Boolean admin;
	
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

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
}
