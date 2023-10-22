package com.samay.CFDemo.model;

public class AddressWrapper {
	private Address address; // This assumes 'address' always contains a single Address object

	// Standard getters and setters
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	// toString method for easy representation, useful for logging
	@Override
	public String toString() {
		return "AddressWrapper{" + "address=" + address + '}';
	}
}
