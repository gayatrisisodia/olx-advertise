package com.olx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class InvalidAdvertisementIdException extends RuntimeException{
	
	private String message;

	public InvalidAdvertisementIdException() {
		this.message = message;
	}
	
	
	public InvalidAdvertisementIdException(String message) {
		super();
		this.message = message;
	}


	@Override
	public String toString() {
		return "Invalid advertisement id "+ this.message;
	}

}
