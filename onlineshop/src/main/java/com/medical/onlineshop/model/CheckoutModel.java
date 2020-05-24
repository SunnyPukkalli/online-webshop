package com.medical.onlineshop.model;

import java.io.Serializable;
import java.util.List;

import com.medical.shopbackend.dto.Address;
import com.medical.shopbackend.dto.Cart;
import com.medical.shopbackend.dto.CartLine;
import com.medical.shopbackend.dto.OrderDetail;
import com.medical.shopbackend.dto.User;

public class CheckoutModel implements Serializable{

	private static final long serialVersionUID = 1L;

	private User user;
	private Address shipping;
	private Cart cart;
	private List<CartLine> cartLines;
	private OrderDetail orderDetail;
	private double checkoutTotal;
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Address getShipping() {
		return shipping;
	}
	public void setShipping(Address shipping) {
		this.shipping = shipping;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public List<CartLine> getCartLines() {
		return cartLines;
	}
	public void setCartLines(List<CartLine> cartLines) {
		this.cartLines = cartLines;
	}
	public OrderDetail getOrderDetail() {
		return orderDetail;
	}
	public void setOrderDetail(OrderDetail orderDetail) {
		this.orderDetail = orderDetail;
	}
	public double getCheckoutTotal() {
		return checkoutTotal;
	}
	public void setCheckoutTotal(double checkoutTotal) {
		this.checkoutTotal = checkoutTotal;
	}
	
	
	
}