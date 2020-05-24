package com.medical.onlineshop.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medical.onlineshop.model.UserModel;
import com.medical.shopbackend.dao.CartLineDAO;
import com.medical.shopbackend.dao.ProductDAO;
import com.medical.shopbackend.dto.Cart;
import com.medical.shopbackend.dto.CartLine;
import com.medical.shopbackend.dto.Product;

@Service("cartService")
public class CartService {
	
	@Autowired
	private CartLineDAO cartLineDAO;

	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private HttpSession session;
	
	
	
	// returns the cart of the user
	public Cart getCart(){	
		return ((UserModel)session.getAttribute("userModel")).getCart();
	}

	
	// returns the entire Cart Line of the user
	public List<CartLine> getCartLines(){
		return cartLineDAO.list(this.getCart().getId());
	}


	public String manageCartLine(int cartLineId, int count) {
		
		// fetch that cartLine
		CartLine cartLine = cartLineDAO.get(cartLineId);	
		if(cartLine == null){
			return "result=error";
		}else{
			
			Product product = cartLine.getProduct();		
			double oldTotal = cartLine.getTotal();
			
			// checking of the product is available
			if(product.getQuantity() < count ){
				return "result =unavailable";
			}
			cartLine.setProductCount(count);
			cartLine.setBuyingPrice(product.getUnitPrice());
			cartLine.setTotal(product.getUnitPrice()*count);			
			cartLineDAO.update(cartLine);
			
			Cart cart = this.getCart();
			cart.setGrandTotal(cart.getGrandTotal() - oldTotal +cartLine.getTotal()); 	
			cartLineDAO.updateCart(cart);
		
			return "result=updated";
		}		
	}


	public String deleteCartLine(int cartLineId) {

		// Fetch cartline  
		CartLine cartLine = cartLineDAO.get(cartLineId);		
		if(cartLine == null){
			return "result=error";
		}else{
			// update the cart
			Cart cart = this.getCart();
			cart.setGrandTotal(cart.getGrandTotal() - cartLine.getTotal());
			cart.setCartLines(cart.getCartLines()-1);
			cartLineDAO.updateCart(cart);			
			//delete the cartline 
			cartLineDAO.remove(cartLine);		
			return "result=deleted";
		}
	}


	public String addCartLine(int productId) {
		
		String response =null;
		
		// Get the users cart
		Cart cart = this.getCart();		
		CartLine cartLine = cartLineDAO.getByCartAndProduct(cart.getId(), productId);		
		if(cartLine == null){
			// Add a new CartLine
			cartLine = new CartLine();
			
			//fetch the product
			Product product = productDAO.getProduct(productId);
			cartLine.setCartId(cart.getId());
			cartLine.setProduct(product);
			cartLine.setBuyingPrice(product.getUnitPrice());
			cartLine.setProductCount(1);
			cartLine.setTotal(product.getUnitPrice());
			cartLine.setAvailable(true);
			cartLineDAO.add(cartLine);			
			
			// update changes to the cart
			cart.setCartLines(cart.getCartLines()+1);
			cart.setGrandTotal(cart.getGrandTotal()+cartLine.getTotal());
			cartLineDAO.updateCart(cart);
			
			response ="added";
		}else{
			
			// check if cartLine has reached the maximum count
			if(cartLine.getProductCount() <3 ){
				//update the product count
				response = this.manageCartLine(cartLine.getId(),cartLine.getProductCount()+1);
			}else{
				response ="result=maximum";
			}
			
		}
		
		
		return response;
	}


	public String validateCartLine() {
		
		Cart cart = this.getCart();
		List<CartLine> cartLines = cartLineDAO.list(cart.getId());
		
		double grandTotal = 0.0;
		int lineCount = 0;
		
		String response = "result=success";
		boolean changed = false;
		Product product = null;
		
		for(CartLine cartLine : cartLines) {					
			product = cartLine.getProduct();
			changed = false;
			// check if the product is active or not
			// if it is not active make the availability of cartLine as false
			if((!product.isActive() && product.getQuantity() == 0) && cartLine.isAvailable()) {
				cartLine.setAvailable(false);
				changed = true;
			}			
			// check if the cartLine is not available
			// check whether the product is active and has at least one quantity available
			if((product.isActive() && product.getQuantity() > 0) && !(cartLine.isAvailable())) {
					cartLine.setAvailable(true);
					changed = true;
			}
			
			// check if the buying price of product has been changed
			if(cartLine.getBuyingPrice() != product.getUnitPrice()) {
				// set the buying price to the new price
				cartLine.setBuyingPrice(product.getUnitPrice());
				// calculate and set the new total
				cartLine.setTotal(cartLine.getProductCount() * product.getUnitPrice());
				changed = true;				
			}
			
			// check if that much quantity of product is available or not
			if(cartLine.getProductCount() > product.getQuantity()) {
				cartLine.setProductCount(product.getQuantity());										
				cartLine.setTotal(cartLine.getProductCount() * product.getUnitPrice());
				changed = true;
				
			}
			
			// changes has happened
			if(changed) {				
				//update the cartLine
				cartLineDAO.update(cartLine);
				// set the result as modified
				response = "result=modified";
			}
			
			grandTotal += cartLine.getTotal();
			lineCount++;
		}
		
		cart.setCartLines(lineCount++);
		cart.setGrandTotal(grandTotal);
		cartLineDAO.updateCart(cart);

		return response;
	}
}
