package com.test.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.model.FoodCourtDetails;

public interface FoodCourtDao  extends JpaRepository<FoodCourtDetails, Serializable> {

}
