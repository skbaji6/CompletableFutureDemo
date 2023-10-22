package com.samay.CFDemo.model;

public class Phone {
	private String phone;

	// Standard getters and setters
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	// toString method for easy representation, useful for logging
	@Override
	public String toString() {
		return "Phone{" + "phone='" + phone + '\'' + '}';
	}
}
