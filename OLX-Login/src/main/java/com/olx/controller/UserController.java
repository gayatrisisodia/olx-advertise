package com.olx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olx.dto.AuthenticationRequest;
import com.olx.dto.User;
import com.olx.security.JwtUtil;
import com.olx.service.UserCustomeDetailService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/olx/auth")
public class UserController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@Autowired 
	UserDetailsService userDetailsService;
	
	@Autowired
	UserCustomeDetailService userCustomeDetailService;
	
	@PostMapping(value="/user/authenticate",consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="It logins the user")
	public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest authenticationRequest){
		
		try {
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), 
					authenticationRequest.getPassword()));
		}
		catch (BadCredentialsException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		String jwtToken = jwtUtil.generateToken(userDetails);
		return new ResponseEntity(jwtToken,HttpStatus.OK);
	}
	
	
	@GetMapping(value="/user/validate/token")
	@ApiOperation(value="It validates the token of authenticated user")
	public ResponseEntity<Boolean> validateToken(@RequestHeader("Authorization") String authToken){
		
		boolean isTokenValid = false;
		try {
			
		String jwtToken = authToken.substring(7,authToken.length());
		String username = jwtUtil.extractUsername(jwtToken);
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
	    isTokenValid = jwtUtil.validateToken(jwtToken, userDetails);
		
		}
		catch (Exception e) {
			return new ResponseEntity<Boolean>(isTokenValid,HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<Boolean>(isTokenValid,HttpStatus.OK);
	
	}
	
	

	@GetMapping(value="/user/details")
	@ApiOperation(value="Returns the user details on the basis of authentication token")
	public ResponseEntity<String> getUserName(@RequestHeader("Authorization") String authToken){
		
		String username = null;
		try {
			
		String jwtToken = authToken.substring(7,authToken.length());
	    username = jwtUtil.extractUsername(jwtToken);
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
	    boolean isTokenValid = jwtUtil.validateToken(jwtToken, userDetails);
		
		}
		catch (Exception e) {
			return new ResponseEntity<String>("This user is not exist",HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<String>(username,HttpStatus.OK);
	
	}
	
	
	@PostMapping(value="/user",consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value="Create the new user")
	public User registerUser(@RequestBody User user){
		
		return userCustomeDetailService.registerUser(user);
	}
	
	@GetMapping(value="/user/allUser",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value="Return all users available in database")
	public List<User> getAllUser(){
		return userCustomeDetailService.getAllUser();
	}
	
	@DeleteMapping(value="/user/logout")
	@ApiOperation(value="Logout the user")
	public ResponseEntity<Boolean> logout(@RequestHeader("auth-token")String authToken){
		
		return null;
	}
	
	
}
