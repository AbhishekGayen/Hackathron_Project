package com.test.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_vendor_details")
public class VendorDetails {
	
	@Id
	@GeneratedValue
	@Column(name = "vendorID")
	private String vendorID;
	
	@Column(name = "vendorName")
	private String vendorName;
	
	@Column(name = "foodCourtID")
	private String foodCourtID;

	public String getVendorID() {
		return vendorID;
	}

	public void setVendorID(String vendorID) {
		this.vendorID = vendorID;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getFoodCourtID() {
		return foodCourtID;
	}

	public void setFoodCourtID(String foodCourtID) {
		this.foodCourtID = foodCourtID;
	}
}
