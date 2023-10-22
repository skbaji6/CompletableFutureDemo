package com.samay.CFDemo.model;

public class Address {
	private String address;

	// Standard getters and setters
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	// toString method for easy representation, useful for logging
	@Override
	public String toString() {
		return "Address{" + "address='" + address + '\'' + '}';
	}
}
