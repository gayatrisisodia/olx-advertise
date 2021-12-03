package com.olx.service;

import java.util.List;
import java.util.Map;


public interface MasterDataDelegate {

	public List<Map> getAllCategories();
	
	public List<Map> getAllStatus();
	
	public String categoryName(int id);

	public String getStatus(int id);

	
}
