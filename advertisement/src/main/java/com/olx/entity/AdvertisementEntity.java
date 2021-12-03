package com.olx.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Advertisement")
public class AdvertisementEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="username")
	private String username;
	@Column(name="adv_title")
	private String  title;
	@Column(name="adv_price")
	private int price;
	@Column(name="adv_category")
	private String category;
	@Column(name="adv_desc")
	private String description;
	@Column(name="adv_status")
	private String status;
	@Column(name="adv_created_date")
	private String createdDate;
	@Column(name="adv_modified_date")
	private String modifiedDate;
	@Column(name="posted_by")
	private String postedBy;
	@Column(name="cat_id")
	private int catId;
	@Column(name="status_id")
	private int statusId;

	
	public AdvertisementEntity() {}




	public AdvertisementEntity(int id, String username, String title, int price, String category, String description,
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
		return "AdvertisementEntity [id=" + id + ", username=" + username + ", title=" + title + ", price=" + price
				+ ", category=" + category + ", description=" + description + ", status=" + status + ", createdDate="
				+ createdDate + ", modifiedDate=" + modifiedDate + ", postedBy=" + postedBy + ", catId=" + catId
				+ ", statusId=" + statusId + "]";
	}
	


}
