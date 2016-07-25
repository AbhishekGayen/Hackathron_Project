package com.test.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.test.model.VendorLoginDetails;
import com.test.model.FoodCourtDetails;
import com.test.model.FoodItemsDetails;
import java.lang.String;

public interface VendorLoginDAO extends JpaRepository<VendorLoginDetails, Serializable>{
	

	List<VendorLoginDetails> findByLoginID(String loginid);
	

}
