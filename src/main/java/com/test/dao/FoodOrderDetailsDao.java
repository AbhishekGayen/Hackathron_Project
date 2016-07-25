package com.test.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.test.model.FoodOrderDetails;
import java.lang.String;
import java.util.List;
import java.lang.Long;

public interface FoodOrderDetailsDao extends JpaRepository<FoodOrderDetails, Serializable>  {
	
	List<FoodOrderDetails> findByVendorID(@Param("vendorid")String vendorid);
	
	@Query("Select a.orderId, a.itemID, a.vendorID from FoodOrderDetails as a "+"  where a.userId = :userid")
	List<FoodOrderDetails> findByUserId(@Param("userid") String userid);
	
	List<FoodOrderDetails> findByOrderId(Long orderid);

}
