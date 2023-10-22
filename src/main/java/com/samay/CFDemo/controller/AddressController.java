package com.samay.CFDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samay.CFDemo.model.FullAddress;
import com.samay.CFDemo.service.AddressService;

@RestController
public class AddressController {

	@Autowired
	private AddressService addressService;

	@GetMapping("/address")
	public FullAddress getBookDetails() throws Exception {
		return addressService.aggregateData();
	}
}
