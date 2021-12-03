package com.olx.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Login Model")
public class User {
	
	@ApiModelProperty("Unique identifier of the user")
	private int id;
	@ApiModelProperty("It represent username of user")
	private String username;
	@ApiModelProperty("It represent password of user")
	private String password;
	@ApiModelProperty("It represent roles of user")
	private String roles;
	@ApiModelProperty("It represent firstname of user")
	private String firstname;
	@ApiModelProperty("It represent lastname of user")
	private String lastname;
	@ApiModelProperty("It represent whether user is active or not")
	private boolean active;
	@ApiModelProperty("It represent mobile number of user")
	private String phone;
	@ApiModelProperty("It represent email address of user")
	private String email;
	
	public User() {}
	
	public User(int id, String username, String password, String roles, String firstname, String lastname,
			boolean active, String phone, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.roles = roles;
		this.firstname = firstname;
		this.lastname = lastname;
		this.active = active;
		this.phone = phone;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", roles=" + roles
				+ ", firstname=" + firstname + ", lastname=" + lastname + ", active=" + active + ", phone=" + phone
				+ ", email=" + email + "]";
	}


	

}
