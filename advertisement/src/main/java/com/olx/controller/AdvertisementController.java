package com.olx.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.standard.Media;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.olx.dto.AdvertisementInfo;
import com.olx.exception.InvalidAdvertisementIdException;
import com.olx.service.AdvertiseService;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/olx/adv")
public class AdvertisementController {

	@Autowired
	AdvertiseService advertiseService;
	
	
	@PutMapping(value="/advertise/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value="Update the advertisement by using id")
	public AdvertisementInfo updateAdvertisementById(@PathVariable("id") int id, @RequestHeader("auth-token") String token ,@RequestBody AdvertisementInfo advertisementInfo) {
		return advertiseService.updateAdvertisementById(id, token, advertisementInfo);
	}
	
	@PostMapping(value="/advertise",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, 
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value="Create new advertisement")
	public AdvertisementInfo createNewAdvertisement(@RequestHeader("auth-token") String auth,@RequestBody AdvertisementInfo advertisementInfo) {
		return advertiseService.createNewAdvertisement(auth, advertisementInfo);
		
	}
	
	@GetMapping(value="/user/advertise", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value="Returns all advertisement created by users")
	public List<AdvertisementInfo> getAllStock(@RequestHeader("auth-token") String authToken) {
		return advertiseService.getAllAdvertisement(authToken);
	}
	
	
     @GetMapping(value="/user/advertise/{advertiseId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}) 
 	@ApiOperation(value="Return advertisement on the basis of advertisement id")
     public AdvertisementInfo getAvertisementById(@RequestHeader("auth-token") String authToken, @PathVariable("advertiseId") int id) { 
		  return advertiseService.getAvertisementById(authToken, id);
		  
     }
	  
	  @DeleteMapping(value="/user/advertise/{advertiseId}")
	  @ApiOperation(value="Delete advertisement on the basis of advertisement id")
	  public boolean deleteAdvertisementById(@PathVariable("advertiseId") int id) {
		  advertiseService.deleteAdvertisemnet(id);
		  return true;
	  }
	  
	  
	  @GetMapping(value="/advertise/search/filtercriteria", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	  @ApiOperation(value="Return advertisement on the basis of filter criteria")
	  public List<AdvertisementInfo> searchByFilterCriteria(@RequestParam(name="searchText",required = false)String searchText,
			  @RequestParam(name ="category",required=false)String category,@RequestParam(name="postedBy",required = false)String postedBy,
			  @RequestParam(name="dateCondition", required = false)String dateCondition,
			  @RequestParam(name="onDate", required = false) String  onDate,
			  @RequestParam(name="fromDate",required = false) String fromDate, 
			  @RequestParam(name="toDate",required = false) String toDate,
			  @RequestParam(name = "sortBy", required = false) String sortBy, @RequestParam(name = "startIndex", required = false) String startIndex,
			  @RequestParam(name = "records", required = false) String records) {
					
		  return advertiseService.searchByFilterCriteria(searchText, category, postedBy, dateCondition, onDate, fromDate, toDate, sortBy, startIndex, startIndex);
	  }
		  
	  
	  @GetMapping(value="/user/search", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	  @ApiOperation(value="Return advertisement on the basis of search text")
	  public List<AdvertisementInfo> searchByText(@RequestParam("searchText") String searchText) {
		return advertiseService.searchByText(searchText);
		 	  
	  }
	

}
