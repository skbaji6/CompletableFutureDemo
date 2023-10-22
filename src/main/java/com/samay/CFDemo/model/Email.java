package com.samay.CFDemo.model;

public class Email {
	private String email;

	// Standard getters and setters
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	// toString method for easy representation, useful for logging
	@Override
	public String toString() {
		return "Email{" + "email='" + email + '\'' + '}';
	}
}
