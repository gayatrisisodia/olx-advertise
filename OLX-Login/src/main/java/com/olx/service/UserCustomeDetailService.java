package com.olx.service;

import java.util.List;

import com.olx.dto.User;

public interface UserCustomeDetailService {
	
	public User registerUser(User user); 
	public List<User> getAllUser();

}
