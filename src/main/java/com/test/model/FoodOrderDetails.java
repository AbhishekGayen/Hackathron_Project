package com.test.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "tbl_order_detail")
public class FoodOrderDetails {

	@Id
	@GeneratedValue(generator = "ORDER_SEQUENCE")
	@GenericGenerator(name = "ORDER_SEQUENCE", strategy = "org.hibernate.id.enhanced.TableGenerator", parameters = {
	@Parameter(name = "order", value = "enhanced_hibernate_sequences"),
	@Parameter(name = "segment_value", value = "orderId"),
	@Parameter(name = "optimizer", value = "pooled"),
	@Parameter(name = "initial_value", value = "1000000"),
	@Parameter(name = "increment_size", value = "1") })
	@Column(name="orderId")
	private Long orderId;
	
	@Column(name = "itemID")
	private String itemID;
	
	@Column(name = "foodcourtID")
	private String foodcourtID;
	
	@Column(name = "vendorID")
	private String vendorID;
	
	@Column(name = "itemcost")
	private Double itemcost;
	
	@Column(name = "quantity")
	private Double quantity;
	
	@Column(name = "rating")
	private String rating;
	
	@Column(name = "complaint")
	private String complaint;
	
	@Column(name = "userId")
	private String userId;


	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getItemID() {
		return itemID;
	}

	public void setItemID(String itemID) {
		this.itemID = itemID;
	}

	public String getFoodcourtID() {
		return foodcourtID;
	}

	public void setFoodcourtID(String foodcourtID) {
		this.foodcourtID = foodcourtID;
	}

	public String getVendorID() {
		return vendorID;
	}

	public void setVendorID(String vendorID) {
		this.vendorID = vendorID;
	}

	public Double getItemcost() {
		return itemcost;
	}

	public void setItemcost(Double itemcost) {
		this.itemcost = itemcost;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getComplaint() {
		return complaint;
	}

	public void setComplaint(String complaint) {
		this.complaint = complaint;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
