package com.olx.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.olx.dto.Category;
import com.olx.dto.Status;
import com.olx.entity.CategoryEntity;
import com.olx.entity.StatusEntity;
import com.olx.repository.CategoryRepository;
import com.olx.repository.StatusRepository;


@Service
public class MasterdataServiceImpl implements MasterdataService{

	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	StatusRepository statusRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public List<Category> getAllCategories() {
		
		/*
		 * List<Category> categories = new ArrayList<>(); categories.add(new Category(1,
		 * "Furniture")); categories.add(new Category(2, "Real Estate")); return
		 * categories;
		 */
		 List<CategoryEntity> advertisementList = categoryRepository.findAll(); return
		 getCategoryDtoListFromEntityList(advertisementList);
		 
	}

	@Override
	public List<Status> getAllStatus() {
		
		/*
		 * List<Status> status =new ArrayList<>(); status.add(new Status(1,"OPEN"));
		 * status.add(new Status(2,"CLOSED")); return status;
		 */
		  List<StatusEntity> statustList = statusRepository.findAll(); return
		  getStatusDtoListFromEntityList(statustList);
		 
		
		}

	private List<Category> getCategoryDtoListFromEntityList(List<CategoryEntity> categoryEntityList){
		   List<Category> categoryDtoList = new ArrayList<Category>();
		   for(CategoryEntity categoryEntity: categoryEntityList) {
			   categoryDtoList.add(getCategoryDtoFromEntity(categoryEntity));
		   }
		   return categoryDtoList;
		}
	
	private Category getCategoryDtoFromEntity(CategoryEntity catEntity) {
	 	//return new Stock(stockEntity.getId(), stockEntity.getName() , stockEntity.getMarketName(), stockEntity.getPrice());
			
		     Category catDto = this.modelMapper.map(catEntity, Category.class);
			 return catDto;
	 }
	
	
	private List<Status> getStatusDtoListFromEntityList(List<StatusEntity> statusEntityList){
		   List<Status> categoryDtoList = new ArrayList<Status>();
		   for(StatusEntity statusEntity: statusEntityList) {
			   categoryDtoList.add(getStatusDtoFromEntity(statusEntity));
		   }
		   return categoryDtoList;
		}
	
	private Status getStatusDtoFromEntity(StatusEntity statusEntity) {
	 	//return new Stock(stockEntity.getId(), stockEntity.getName() , stockEntity.getMarketName(), stockEntity.getPrice());
			
		Status statusDto = this.modelMapper.map(statusEntity, Status.class);
		return statusDto;
	 }

	

	@Override
	public String getStatusById(int id) {
		Optional<StatusEntity> opStockEntity = statusRepository.findById(id);
		String status = opStockEntity.get().getStatus();
		return status;
	}

	@Override
	public String getCategoryById(int id) {
		Optional<CategoryEntity> opStockEntity = categoryRepository.findById(id);
		return opStockEntity.get().getCategory();
	}

	
}
