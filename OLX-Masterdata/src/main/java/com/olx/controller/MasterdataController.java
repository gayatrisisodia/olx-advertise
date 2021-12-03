package com.olx.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olx.dto.Category;
import com.olx.dto.Status;
import com.olx.service.MasterdataService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/olx/masterdata")
public class MasterdataController {

	@Autowired
	MasterdataService masterdataService;
	
	@GetMapping(value="/advertise/category", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}) 
	@ApiOperation(value="Returns all category")
	public List<Category> getAllCategories() {
		return masterdataService.getAllCategories();
	}
	
	@GetMapping(value="/advertise/status", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value="Returns all status")
	public List<Status> getAllStatus() {
	   return masterdataService.getAllStatus();
	}
	
	@GetMapping(value="/advertise/category/{catId}", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}) 
	@ApiOperation(value="Returns category name by category id")
	public String getCategoryName(@PathVariable("catId")int id) {
		return masterdataService.getCategoryById(id);
	}
	
	@GetMapping(value="/advertise/status/{statusId}", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value="Returns status by status id")
	public String getStatusName(@PathVariable("statusId")int id) {
	   return masterdataService.getStatusById(id);
	}
}
