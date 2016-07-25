package com.test.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.test.model.FoodItemsDetails;
import java.lang.String;
import java.util.List;

public interface FoodItemsDetailsDao extends JpaRepository<FoodItemsDetails, Serializable> {

	
	@Query("Select a.itemID, a.itemName, a.itemCost, a.type from FoodItemsDetails as a "+"  where a.vendorID = :vendorid")
	List<FoodItemsDetails> findByVendorID(@Param("vendorid")String vendorid);
	
	List<FoodItemsDetails> findByItemID(String itemid);
	
}
