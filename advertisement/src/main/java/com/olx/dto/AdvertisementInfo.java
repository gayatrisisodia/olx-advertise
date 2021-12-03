package com.olx.dto;

import java.time.LocalDate;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Advertisement Model")
public class AdvertisementInfo {
	
	@ApiModelProperty("Unique identifier of the user")
	private int id;
	@ApiModelProperty("It represent username of user")
	private String username;
	@ApiModelProperty("It represent title of advertisement")
	private String  title;
	@ApiModelProperty("It represent price for the goods")
	private int price;
	@ApiModelProperty("It represent category of advertisement")
	private String category;
	@ApiModelProperty("It represent description of advertisement")
	private String description;
	@ApiModelProperty("It represent status of advertisement")
	private String status;
	@ApiModelProperty("It represent creation date of advertisement")
	private String createdDate;
	@ApiModelProperty("It represent modification date of advertisement")
	private String modifiedDate;
	@ApiModelProperty("It represent by whom advertisement is posted")
	private String postedBy;
	@ApiModelProperty("It represent category id of advertisement")
	private int catId;
	@ApiModelProperty("It represent statud id of advertisement")
	private int statusId;

	public AdvertisementInfo() {
		super();
	}

	public AdvertisementInfo(int id, String username, String title, int price, String category, String description,
			String status, String createdDate, String modifiedDate, String postedBy, int catId, int statusId) {
		super();
		this.id = id;
		this.username = username;
		this.title = title;
		this.price = price;
		this.category = category;
		this.description = description;
		this.status = status;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.postedBy = postedBy;
		this.catId = catId;
		this.statusId = statusId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	
	

	public int getCatId() {
		return catId;
	}




	public void setCatId(int catId) {
		this.catId = catId;
	}




	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	@Override
	public String toString() {
		return "AdvertisementInfo [id=" + id + ", username=" + username + ", title=" + title + ", price=" + price
				+ ", category=" + category + ", description=" + description + ", status=" + status + ", createdDate="
				+ createdDate + ", modifiedDate=" + modifiedDate + ", postedBy=" + postedBy + ", catId=" + catId
				+ ", statusId=" + statusId + "]";
	}


	
	

}
