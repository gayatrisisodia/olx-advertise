package com.olx.service;

import org.springframework.http.ResponseEntity;

public interface LoginDataDelegate {

	public boolean isValidateToken(String authToken);
	public String userDetail(String authToken);
}
