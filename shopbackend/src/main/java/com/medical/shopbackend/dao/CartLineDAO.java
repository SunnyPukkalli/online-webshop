package com.medical.shopbackend.dao;

import java.util.List;

import com.medical.shopbackend.dto.Cart;
import com.medical.shopbackend.dto.CartLine;
import com.medical.shopbackend.dto.OrderDetail;

public interface CartLineDAO {

	
	// Common  methods
	public List<CartLine> list(int cartId);
	public CartLine get(int id);	
	public boolean add(CartLine cartLine);
	public boolean update(CartLine cartLine);
	public boolean remove(CartLine cartLine);
	
	// fetch the CartLine based on cartId and productId
	public CartLine getByCartAndProduct(int cartId, int productId);		
		
	// updating the cart
	boolean updateCart(Cart cart);
	
	// list of available cartLine
	public List<CartLine> listAvailable(int cartId);
	
	// adding order details
	boolean addOrderDetail(OrderDetail orderDetail);
	
}
