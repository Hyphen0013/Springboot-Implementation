package com.hyphen.dto;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class UserDTO {

	private int id;
	private String email;
	private String name;
	private String password;
	private String mobile;
	private int role_type;
	private String role;
	private Date created_at;

	public UserDTO(String email, String name, String password, String mobile, int role_type, String role,
			Date created_at) {
		super();
		this.email = email;
		this.name = name;
		this.password = password;
		this.mobile = mobile;
		this.role_type = role_type;
		this.role = role;
		this.created_at = created_at;
	}

	public UserDTO() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getRole_type() {
		return role_type;
	}

	public void setRole_type(int role_type) {
		this.role_type = role_type;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", email=" + email + ", name=" + name + ", password=" + password + ", mobile="
				+ mobile + ", role_type=" + role_type + ", role=" + role + ", created_at=" + created_at + "]";
	}

}
