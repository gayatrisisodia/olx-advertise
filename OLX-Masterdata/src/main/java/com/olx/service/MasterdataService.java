package com.olx.service;

import java.util.List;

import com.olx.dto.Category;
import com.olx.dto.Status;

public interface MasterdataService {

	public List<Category> getAllCategories();
	public List<Status> getAllStatus();
	
	public String getCategoryById(int id);
	public String getStatusById(int id);
	
	
}
