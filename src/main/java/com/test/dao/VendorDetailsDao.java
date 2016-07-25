package com.test.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.test.model.VendorDetails;

public interface VendorDetailsDao  extends JpaRepository<VendorDetails, Serializable>  {

	@Query("Select a.vendorID, a.vendorName from VendorDetails as a "+"  where a.foodCourtID = :fcId")
	List<VendorDetails> findByFoodCourtID(@Param("fcId")String fcId);
}
