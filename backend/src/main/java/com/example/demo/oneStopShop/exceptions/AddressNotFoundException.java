package com.example.demo.oneStopShop.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class AddressNotFoundException extends RuntimeException {
	
	public AddressNotFoundException(String message) {
		super(message);
	}

}
