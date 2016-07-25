package com.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.test.dao.VendorDetailsDao;
import com.test.dao.VendorLoginDAO;
import com.test.model.VendorLoginDetails;

@RestController
@RequestMapping("/hackathon")
public class VendorLoginController {

	@Autowired VendorLoginDAO loginDAO;
	
	@RequestMapping(value = "/vendorLogin", method = RequestMethod.POST)
	public String storeVendorDetails(@RequestBody String vendorDetails){
		VendorLoginDetails loginDetails = new VendorLoginDetails();
		String[] loginId= vendorDetails.substring(1, (vendorDetails.length()-1)).split(",");
		List<VendorLoginDetails> loginDetailsList = loginDAO.findByLoginID(loginId[0]);
		if(loginDetailsList.size() == 0){
			loginDetails.setLoginID(loginId[0].toString());
			loginDetails.setPassword(loginId[1]);
			loginDetails.setType("vendor");
			loginDetails.setVendorId(loginId[2].toString());
			
			loginDAO.save(loginDetails);
		}
		return "success";
	}
	@RequestMapping(value ="/login", method = RequestMethod.POST)
	public String getLoginDetails(@RequestBody String loginDetails){
		String[] logindetail = loginDetails.substring(1, (loginDetails.length()-1)).split(",");
		String type = "";
		List<VendorLoginDetails> vendorLoginDetails = loginDAO.findByLoginID(logindetail[0]);
		for (VendorLoginDetails vendorLoginDetails2 : vendorLoginDetails) {
			if(vendorLoginDetails2.getType().equalsIgnoreCase("admin")){
				type = "admin";
			}else{
				
				type = "vendor" + ","+ vendorLoginDetails2.getVendorId(); 
			}
		}
		return type;
	}
}
