package com.test.controller;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.test.dao.UserDao;
import com.test.model.CustomerDetails;

/**
 * Handles requests for the application Customer/vendor page according to the user.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired UserDao userDao;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		String userName = System.getProperty("user.name");
		System.out.println("Value of the Username: "+userName);
		List<CustomerDetails> customerDetails = userDao.findByUsername(userName);
		
		System.out.println("Size of the list : "+customerDetails.size());
		//System.out.println(user2.getPassword());
		if(customerDetails.size() > 0){
			return "FoodCourtCustomerHome";
		}else{
			return "vendorAdminLogin";
		}	
	}
}
