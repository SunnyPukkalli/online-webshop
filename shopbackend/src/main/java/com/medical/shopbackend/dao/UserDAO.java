package com.medical.shopbackend.dao;

import java.util.List;

import com.medical.shopbackend.dto.Address;
import com.medical.shopbackend.dto.User;

public interface UserDAO {

	// to add user
	public boolean addUser(User user);
	
	// to fetch user
	public User getUserByEmail(String email);
	public User getUser(int id);
		
	// Address operations
/*	public Address getBillingAddress(User user);
	public List<Address> getShippingAddress(User user);
*/
	
	// Alternate Methods for above
	 public Address getBillingAddress(int userId);
	 public List<Address> getShippingAddress(int userId);
	 public boolean addAddress(Address address);
	 public boolean updateAddress(Address address);
	 public Address getAddress(int addressId);
	
}
