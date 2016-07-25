package com.test.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.test.model.CustomerDetails;
import com.test.model.User;
import java.lang.String;
import java.util.List;

@Repository
public interface UserDao extends JpaRepository<CustomerDetails, Serializable> {
	
	/*@Query("select u from User u where u.emailAddress = ?1")
	  User findByEmailAddress(String emailAddress);*/
	
	/*List<CustomerDetails> findByEmailId(String emailid);*/
   List<CustomerDetails> findByUsername(String username);
	
}
