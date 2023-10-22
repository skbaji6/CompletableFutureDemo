package com.samay.CFDemo.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.samay.CFDemo.exception.ServiceException;
import com.samay.CFDemo.model.AddressWrapper;
import com.samay.CFDemo.model.EmailsWrapper;
import com.samay.CFDemo.model.FullAddress;
import com.samay.CFDemo.model.PhonesWrapper;

@Service
public class AddressService {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private Executor taskExecutor;

	private final String PHONE_DETAILS_API_URL = "http://localhost:3000/phone";
	private final String EMAIL_DETAILS_API_URL = "http://localhost:3001/email";
	private final String POSTAL_ADDRESS_DETAILS_API_URL = "http://localhost:3002/postal";

	private PhonesWrapper getPhoneDetails() {
		try {
			ResponseEntity<PhonesWrapper> response = restTemplate.getForEntity(PHONE_DETAILS_API_URL,
					PhonesWrapper.class);
			return response.getBody();
		} catch (Exception e) {
			// Handle or log exception
			return null;
		}
	}

	private EmailsWrapper getEmailDetails() {
		try {
			ResponseEntity<EmailsWrapper> response = restTemplate.getForEntity(EMAIL_DETAILS_API_URL,
					EmailsWrapper.class);
			return response.getBody();
		} catch (Exception e) {
			// Handle or log exception
			return null;
		}
	}

	private AddressWrapper getPostalAddressDetails() throws ServiceException {
		try {
			ResponseEntity<AddressWrapper> response = restTemplate.getForEntity(POSTAL_ADDRESS_DETAILS_API_URL,
					AddressWrapper.class);
			return response.getBody();
		} catch (HttpClientErrorException e) {
			throw new ServiceException("Error occurred during the API call", e);
		}
	}

	public FullAddress aggregateData() throws Exception {
		CompletableFuture<PhonesWrapper> phones = CompletableFuture.supplyAsync(() -> getPhoneDetails(), taskExecutor);

		CompletableFuture<EmailsWrapper> emails = CompletableFuture.supplyAsync(() -> getEmailDetails(), taskExecutor);

		CompletableFuture<AddressWrapper> addresses = CompletableFuture.supplyAsync(() -> {
			try {
				return getPostalAddressDetails();
			} catch (ServiceException e) {
				throw new RuntimeException(e);
			}
		}, taskExecutor);

		// Wait until all futures are complete
		CompletableFuture.allOf(phones, emails, addresses).join();

		// Create a new FullAddress instance and set values, checking for null in case
		// of service failure
		FullAddress fullAddress = new FullAddress();

		if (phones.get() != null) {
			fullAddress.setPhone(phones.get().getPhones());
		}
		if (emails.get() != null) {
			fullAddress.setEmail(emails.get().getEmails());
		}

		// Extract the address details and handle the FWCException if it was thrown
		try {
			fullAddress.setAddress(addresses.get().getAddress());
		} catch (Exception e) {
			// Check if the cause is FWCException and if so, throw it
			if (e.getCause() instanceof ServiceException) {
				throw (ServiceException) e.getCause();
			} else {
				throw e; // Rethrow the current exception if it's not an FWCException
			}
		}

		return fullAddress;
	}
}

// Assuming FWCException is a custom exception you've defined.
