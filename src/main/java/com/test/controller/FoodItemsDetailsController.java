package com.test.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.dao.FoodItemsDetailsDao;
import com.test.model.FoodItemsDetails;


@RestController
@RequestMapping("/hackathon")
public class FoodItemsDetailsController {

private static final org.slf4j.Logger logger = LoggerFactory.getLogger(VendorDetailsController.class);
	
	@Autowired FoodItemsDetailsDao foodItemsDetailsDao;
	
	@RequestMapping(value = "/itemsDetails", method = RequestMethod.POST)
	public List<FoodItemsDetails> getFoodItemsDetails(@RequestBody String id){
		String vendorid = id.substring(1, (id.length()-1));
		List<FoodItemsDetails> foodItemsDetails =  foodItemsDetailsDao.findByVendorID(vendorid);
		return foodItemsDetails;
	}
	
	@RequestMapping(value = "/deleteItemsDetails", method = RequestMethod.POST)
	public void deleteItemId(@RequestBody String id){
		
		FoodItemsDetails itemsDetails = new FoodItemsDetails();
		List<FoodItemsDetails> foodItemsDetails = foodItemsDetailsDao.findByItemID(id);
		for (FoodItemsDetails foodItemsDetailsList : foodItemsDetails) {
			itemsDetails.setItemCost(foodItemsDetailsList.getItemCost());
			itemsDetails.setItemName(foodItemsDetailsList.getItemName());
			itemsDetails.setType(foodItemsDetailsList.getType());
			itemsDetails.setVendorID(foodItemsDetailsList.getVendorID());
		}
		itemsDetails.setItemID(id);
		
		foodItemsDetailsDao.delete(itemsDetails);
	
	}
	
	
}
