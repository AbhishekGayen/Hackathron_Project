package com.test.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.model.CustomerDetails;
import com.test.model.OrderStatusDetails;

public interface OrderStatusDao extends JpaRepository<OrderStatusDetails, Serializable> {

}
