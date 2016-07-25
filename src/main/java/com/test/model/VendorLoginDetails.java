package com.test.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_admin_details")	
public class VendorLoginDetails {
@Id
@GeneratedValue
@Column(name="id")
private Long id;
	
	@Column(name = "loginID", unique= true)
	private String loginID;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "VendorId")
	private String VendorId;

	public String getVendorId() {
		return VendorId;
	}

	public void setVendorId(String vendorId) {
		VendorId = vendorId;
	}

	public String getLoginID() {
		return loginID;
	}

	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

}
