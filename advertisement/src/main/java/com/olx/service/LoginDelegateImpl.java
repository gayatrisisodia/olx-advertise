package com.olx.service;

import java.util.List;
import java.util.Map;

import javax.persistence.criteria.Predicate.BooleanOperator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class LoginDelegateImpl implements LoginDataDelegate {

	@Autowired
	RestTemplate restTemplate;
	
	@Bean
	@LoadBalanced
	public RestTemplate getLoginRestTemplate() {
		return new RestTemplate();
	}
	
	@Override
	@CircuitBreaker(name = "AUTH-TOKEN-VALIDATE", fallbackMethod = "fallBackTokenValidation")
	public boolean isValidateToken(String authToken) {
		HttpHeaders headers =  new HttpHeaders();
		headers.set("Authorization", authToken);
		HttpEntity entity = new HttpEntity(headers);
		ResponseEntity<Boolean> result = this.restTemplate.exchange("http://API-GATEWAY/olx/auth/user/validate/token",HttpMethod.GET,entity, Boolean.class);
		return result.getBody();
	}
	

	  public boolean fallBackTokenValidation(Throwable ex){
	     return false;
	  }

	@Override
	public String userDetail(String authToken) {
		HttpHeaders headers =  new HttpHeaders();
		headers.set("Authorization", authToken);
		HttpEntity entity = new HttpEntity(headers);
		ResponseEntity<String> result = this.restTemplate.exchange("http://API-GATEWAY/olx/auth/user/details",HttpMethod.GET,entity, String.class);
		return result.getBody();
	}

	  
	
}
