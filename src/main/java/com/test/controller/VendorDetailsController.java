package com.test.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.dao.VendorDetailsDao;
import com.test.model.VendorDetails;

@RestController
@RequestMapping("/hackathon")
public class VendorDetailsController {
	
private static final org.slf4j.Logger logger = LoggerFactory.getLogger(VendorDetailsController.class);
	
	@Autowired VendorDetailsDao vendorDetailsDao;
	
	
	@RequestMapping(value = "/vendor", method = RequestMethod.POST)
	public List<VendorDetails> getVendorDetails(@RequestBody String id){
		String fcId = id.substring(1, (id.length()-1));
		List<VendorDetails> vendorList = vendorDetailsDao.findByFoodCourtID(fcId);
		return vendorList;
	}
}
