package com.samay.CFDemo.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FullAddress {
	private Address address;
	private Email email;
	private Phone phone;
}
