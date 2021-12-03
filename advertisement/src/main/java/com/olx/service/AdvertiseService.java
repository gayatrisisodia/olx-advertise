package com.olx.service;


import java.time.LocalDate;
import java.util.List;

import com.olx.dto.AdvertisementInfo;


public interface AdvertiseService {
	
	public AdvertisementInfo createNewAdvertisement(String auth,AdvertisementInfo advertisementInfo);
	public AdvertisementInfo updateAdvertisementById(int id, String token, AdvertisementInfo advertisementInfo);
    public AdvertisementInfo getAvertisementById(String authToken, int id);
    public List<AdvertisementInfo> getAllAdvertisement(String authToken);
    public List<AdvertisementInfo> searchByFilterCriteria(String searchText,String category,String postedBy,String dateCondition,
    		String onDate, String fromDate, String toDate, String sortBy, String startIndex, String reccords);
    public List<AdvertisementInfo> searchByText(String searchText);
    public boolean deleteAdvertisemnet(int advertisementId);
				
}
