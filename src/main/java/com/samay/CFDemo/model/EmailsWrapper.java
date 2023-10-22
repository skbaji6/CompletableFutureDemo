package com.samay.CFDemo.model;

public class EmailsWrapper {
	private Email emails; // This assumes 'emails' always contains a single Email object

	// Standard getters and setters
	public Email getEmails() {
		return emails;
	}

	public void setEmails(Email emails) {
		this.emails = emails;
	}

	// toString method for easy representation, useful for logging
	@Override
	public String toString() {
		return "EmailsWrapper{" + "emails=" + emails + '}';
	}
}
