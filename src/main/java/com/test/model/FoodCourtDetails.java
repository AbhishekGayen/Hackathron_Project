package com.test.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_foodcourt_details")
public class FoodCourtDetails {
	
	@Id
	@GeneratedValue
	@Column(name = "foodCourtID")
	private String foodCourtID;
	
	@Column(name = "foodCourtName" , unique = true)
	private String foodCourtName;

	public String getFoodCourtID() {
		return foodCourtID;
	}

	public void setFoodCourtID(String foodCourtID) {
		this.foodCourtID = foodCourtID;
	}

	public String getFoodCourtName() {
		return foodCourtName;
	}

	public void setFoodCourtName(String foodCourtName) {
		this.foodCourtName = foodCourtName;
	}
}
