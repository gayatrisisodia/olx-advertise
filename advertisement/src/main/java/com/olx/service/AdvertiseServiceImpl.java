package com.olx.service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olx.controller.AdvertisementController;
import com.olx.dto.AdvertisementInfo;
import com.olx.entity.AdvertisementEntity;
import com.olx.exception.InvalidAdvertisementIdException;
import com.olx.exception.InvalidAuthTokenException;
import com.olx.repository.AdvertisementRepository;


@Service
public class AdvertiseServiceImpl implements AdvertiseService {

	 @Autowired
	 MasterDataDelegate masterDataDelegate;
	
	
	 @Autowired
	 LoginDataDelegate loginDataDelegate;
	
	
	 @Autowired
	 AdvertisementRepository repository;
	
	 @Autowired
	 org.modelmapper.ModelMapper modelMapper;
	 
	 @Autowired
	 EntityManager entityManager;
	
	
	//For updating the advertisement
	@Override
	public AdvertisementInfo updateAdvertisementById(int id, String token, AdvertisementInfo advertisementInfo) {
		
		if(!loginDataDelegate.isValidateToken(token)) {
		}
		
		else {
			String name = loginDataDelegate.userDetail(token);
			String catName = masterDataDelegate.categoryName(advertisementInfo.getCatId());
			String status = masterDataDelegate.getStatus(advertisementInfo.getStatusId());
			advertisementInfo.setUsername(name);
		
			Optional<AdvertisementEntity> opAdvEntity = repository.findById(id);
			if(opAdvEntity.isPresent()) {
				AdvertisementEntity advEntity  = opAdvEntity.get();
				advEntity.setCategory(catName);
				advEntity.setCreatedDate(advertisementInfo.getCreatedDate());
				advEntity.setDescription(advertisementInfo.getDescription());
				advEntity.setModifiedDate(advertisementInfo.getModifiedDate());
				advEntity.setPostedBy(advertisementInfo.getPostedBy());
				advEntity.setPrice(advertisementInfo.getPrice());
				advEntity.setUsername(name);
				advEntity.setStatus(status);
				advEntity.setTitle(advertisementInfo.getTitle());
				repository.save(advEntity);
				return getAdvertisementDtoFromEntity(advEntity);
			}
		}
		
		return null;
		
	}

	//For getting the advertisement by id
	@Override
	public AdvertisementInfo getAvertisementById(String authToken, int id) {
		// TODO Auto-generated method stub
		//return AdvertisementController.advertisementMap.get(id);
		
		if(!loginDataDelegate.isValidateToken(authToken)) {
			
			throw new InvalidAuthTokenException();
		}
		
		else {
			Optional<AdvertisementEntity> opStockEntity = repository.findById(id);
			if(opStockEntity.isPresent()) {
				AdvertisementEntity stockEntity = opStockEntity.get();
				return getAdvertisementDtoFromEntity(stockEntity);
			}		
		}
	
		throw new InvalidAdvertisementIdException(" "+id);
	}

	
	 private AdvertisementEntity getAdvertisementEntityFromDto(AdvertisementInfo advertisementInfo) {
	    	//return new StockEntity(stock.getName() , stock.getMarket(), stock.getPrice());
		 
		 AdvertisementEntity advEntity = this.modelMapper.map(advertisementInfo,AdvertisementEntity.class);
		 return advEntity;
	    }
	
	private AdvertisementInfo getAdvertisementDtoFromEntity(AdvertisementEntity advEntity) {
 	//return new Stock(stockEntity.getId(), stockEntity.getName() , stockEntity.getMarketName(), stockEntity.getPrice());
		
		 AdvertisementInfo advDto = this.modelMapper.map(advEntity, AdvertisementInfo.class);
		 return advDto;
 }
 
	
	private List<AdvertisementInfo> getAdvertisementDtoListFromEntityList(List<AdvertisementEntity> advEntityList){
		   List<AdvertisementInfo> advDtoList = new ArrayList<AdvertisementInfo>();
		   for(AdvertisementEntity stockEntity: advEntityList) {
			   advDtoList.add(getAdvertisementDtoFromEntity(stockEntity));
		   }
		   return advDtoList;
		}
	
	//For getting all the advertisement
	@Override
	public List<AdvertisementInfo> getAllAdvertisement(String authToken) {
		
		//return AdvertisementController.advertisementMap.values();
		
		if(!loginDataDelegate.isValidateToken(authToken)) {
			throw new InvalidAuthTokenException();
		}
		
		else {
			List<AdvertisementEntity> advertisementList = repository.findAll();
			return getAdvertisementDtoListFromEntityList(advertisementList);
		}
		
	}
	
	//For creating the advertisement
	@Override
	public AdvertisementInfo createNewAdvertisement(String auth, AdvertisementInfo advertisementInfo) {
		
		AdvertisementEntity advertisementEntity = null;
		if(!loginDataDelegate.isValidateToken(auth)) {
			throw new InvalidAuthTokenException();	 
		}
		
		else {
			String name = loginDataDelegate.userDetail(auth);
			advertisementInfo.setUsername(name);
			String catName = masterDataDelegate.categoryName(advertisementInfo.getCatId());
			String status = masterDataDelegate.getStatus(advertisementInfo.getStatusId());
			advertisementInfo.setCategory(catName);
			advertisementInfo.setCreatedDate("2-12-2021");
			advertisementInfo.setModifiedDate("2-12-2021");
			advertisementInfo.setPostedBy(name);
			advertisementInfo.setStatus(status);
			
			advertisementEntity = getAdvertisementEntityFromDto(advertisementInfo);
			advertisementEntity = repository.save(advertisementEntity);	
			return getAdvertisementDtoFromEntity(advertisementEntity);	
		}
	}

	@Override
	public List<AdvertisementInfo> searchByFilterCriteria(String searchText, String category, String postedBy,
		String dateCondition, String onDate,String fromDate, String toDate, String sortBy, String startIndex, String reccords) {
		/*
		 * List<Map> categoriesList = masterDataDelegate.getAllCategories();
		 * List<String> strings = (List<String>) categoriesList.stream() .map(object ->
		 * Objects.toString(object, null)) .collect(Collectors.toList());
		 */
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(AdvertisementEntity.class);
		Root<AdvertisementEntity> rootEntity = criteriaQuery.from(AdvertisementEntity.class);
		javax.persistence.criteria.Predicate titlePredicate = criteriaBuilder.equal(rootEntity.get("title"), searchText);
		javax.persistence.criteria.Predicate categoryPredicate = criteriaBuilder.equal(rootEntity.get("category"), category);
		javax.persistence.criteria.Predicate postedByPredicate = criteriaBuilder.equal(rootEntity.get("postedBy"), postedBy);
		
		javax.persistence.criteria.Predicate finalPredicate = criteriaBuilder.and(titlePredicate,categoryPredicate);
		criteriaQuery.where(finalPredicate);
		TypedQuery<AdvertisementEntity> query = entityManager.createQuery(criteriaQuery);
		List<AdvertisementEntity> advertisementEntityList = query.getResultList();
		
		System.out.println("result-->"+advertisementEntityList);
		
		
		return getAdvertisementDtoListFromEntityList(advertisementEntityList);
	}

	//For searching the advertisement by text
	@Override
	public List<AdvertisementInfo> searchByText(String searchText) {
		List<AdvertisementEntity> list = repository.getAdvertisementByTitle(searchText);
		return getAdvertisementDtoListFromEntityList(list);
	}

	@Override
	public boolean deleteAdvertisemnet(int advertisementId) {
		repository.deleteById(advertisementId);
		return true;
	}
	
}
