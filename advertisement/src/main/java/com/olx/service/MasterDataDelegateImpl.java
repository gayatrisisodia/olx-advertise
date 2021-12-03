package com.olx.service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;


@Service
public class MasterDataDelegateImpl implements MasterDataDelegate{

	
	@Autowired
	RestTemplate restTemplate;
	
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	
	@Override
	@CircuitBreaker(name = "CATEGORY-CIRCUIT-BREAKER", fallbackMethod = "fallBackGetAllCategories")
	public List<Map> getAllCategories() {
		//List<Map> list = this.restTemplate.getForObject("http://localhost:9001/olx/masterdata/advertise/category", List.class);
		List<Map> list = this.restTemplate.getForObject("http://API-GATEWAY/olx/masterdata/advertise/category", List.class);
		
		 List<String> strings = (List<String>) list.stream()
				   .map(object -> Objects.toString(object, null))
				   .collect(Collectors.toList());
		
		return list;
	}

	
	  @Override
	 // @CircuitBreaker(name = "STATUS-CIRCUIT-BREAKER", fallbackMethod ="fallBackGetAllStatus") 
	  public List<Map> getAllStatus() {
	   List<Map> list = this.restTemplate.getForObject("http://API-GATEWAY/olx/masterdata/advertise/status",List.class);   
	  return list; 
	  }
	
	  public List<Map> fallBackGetAllCategories(Throwable ex){
	  System.out.println("MasterData categories call failed : "+ex);
	  return null;
	  }


	@Override
	public String categoryName(int id) {
		String name = this.restTemplate.getForObject("http://API-GATEWAY/olx/masterdata/advertise/category/"+id,String.class); 
		return name; 
	}


	@Override
	public String getStatus(int id) {
		String name = this.restTemplate.getForObject("http://API-GATEWAY/olx/masterdata/advertise/status/"+id,String.class); 
		return name; 
	}


	
		/*
		 * public List<Map> fallBackGetAllStatus(int x, Throwable ex){
		 * System.out.println("MasterData status call failed : "+ex); return null; }
		 */
	 

}
