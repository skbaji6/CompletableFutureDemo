package com.samay.CFDemo.model;

public class PhonesWrapper {
	private Phone phones; // This assumes 'phones' always contains a single Phone object

	// Standard getters and setters
	public Phone getPhones() {
		return phones;
	}

	public void setPhones(Phone phones) {
		this.phones = phones;
	}

	// toString method for easy representation, useful for logging
	@Override
	public String toString() {
		return "PhonesWrapper{" + "phones=" + phones + '}';
	}
}
