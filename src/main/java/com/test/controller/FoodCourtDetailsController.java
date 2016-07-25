package com.test.controller;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.dao.FoodCourtDao;
import com.test.model.FoodCourtDetails;

@RestController
@RequestMapping("/hackathon")
public class FoodCourtDetailsController {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(FoodCourtDetailsController.class);
	
	@Autowired FoodCourtDao foodCourtDao;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/one", method = RequestMethod.GET)
	public List<FoodCourtDetails> getFoodCourtDetails(){
		//FoodCourtDetails foodCourtDetails = new FoodCourtDetails();
	   List<FoodCourtDetails> foodCourtDetailsList = foodCourtDao.findAll();
	
		return foodCourtDetailsList;
	}
}
