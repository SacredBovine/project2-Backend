package com.revature.models;

import java.util.Objects;

import org.springframework.stereotype.Component;

@Component
public class UserDTO {
	
	private int userId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	
	public UserDTO() {
		super();
	}
	
	public UserDTO(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, firstName, lastName, password, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDTO other = (UserDTO) obj;
		return Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(password, other.password)
				&& userId == other.userId;
	}

	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", " + (firstName != null ? "firstName=" + firstName + ", " : "")
				+ (lastName != null ? "lastName=" + lastName + ", " : "")
				+ (email != null ? "email=" + email + ", " : "") + (password != null ? "password=" + password : "")
				+ "]";
	}
	
}