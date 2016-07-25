package com.test.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_itemdetails")
public class FoodItemsDetails {

	@Id
	@GeneratedValue
	@Column(name = "itemID")
	private String itemID;
	
	@Column(name = "itemName")
	private String itemName;
	
	@Column(name = "itemCost")
	private String itemCost;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "vendorID")
	private String vendorID;

	public String getItemID() {
		return itemID;
	}

	public void setItemID(String itemID) {
		this.itemID = itemID;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemCost() {
		return itemCost;
	}

	public void setItemCost(String itemCost) {
		this.itemCost = itemCost;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVendorID() {
		return vendorID;
	}

	public void setVendorID(String vendorID) {
		this.vendorID = vendorID;
	}
	
}
