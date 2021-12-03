package com.olx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.olx.entity.AdvertisementEntity;

public interface AdvertisementRepository extends JpaRepository<AdvertisementEntity, Integer>,JpaSpecificationExecutor<AdvertisementEntity> {

	
	@Query("SELECT adv FROM AdvertisementEntity adv WHERE adv.title LIKE %:title%")
	//@Query("SELECT adv FROM AdvertisementEntity adv WHERE adv.title =:title OR" + "(adv.category like %:title%)")
	List<AdvertisementEntity> getAdvertisementByTitle(@Param("title") String text);
}
