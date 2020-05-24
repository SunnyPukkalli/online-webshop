package com.medical.onlineshop.model;

import java.io.Serializable;

import com.medical.shopbackend.dto.Address;
import com.medical.shopbackend.dto.User;

public class RegisterModel implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	private Address billing;
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Address getBilling() {
		return billing;
	}
	public void setBilling(Address billing) {
		this.billing = billing;
	}
	
	
}
