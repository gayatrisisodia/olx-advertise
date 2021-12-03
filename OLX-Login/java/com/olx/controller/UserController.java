package com.olx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olx.dto.AuthenticationRequest;
import com.olx.security.JwtUtil;

@RestController
@RequestMapping("/olx")
public class UserController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@Autowired 
	UserDetailsService userDetailsService;
	
	@PostMapping(value="/user/authenticate",consumes = MediaType.APPLICATION_JSON_VALUE)
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
	public ResponseEntity<Boolean> validateToken(@RequestHeader("Authorization") String authToken){
		
		boolean isTokenValid = false;
		try {
			
		String jwtToken = authToken.substring(7,authToken.length());
		String username = jwtUtil.extractUsername(jwtToken);
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		boolean isTokenValid = jwtUtil.validateToken(jwtToken, userDetails);
		
		}
		catch (Exception e) {
			return new ResponseEntity<Boolean>(isTokenValid,HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<Boolean>(isTokenValid,HttpStatus.OK);
	
	}
}
