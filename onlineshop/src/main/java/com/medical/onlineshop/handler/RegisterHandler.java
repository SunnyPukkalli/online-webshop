package com.medical.onlineshop.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Component;

import com.medical.onlineshop.model.RegisterModel;
import com.medical.shopbackend.dao.UserDAO;
import com.medical.shopbackend.dto.Address;
import com.medical.shopbackend.dto.Cart;
import com.medical.shopbackend.dto.User;

@Component
public class RegisterHandler {

	@Autowired
	private UserDAO userDAO;

	public RegisterModel init() {
		return new RegisterModel();
	}

	public void addUser(RegisterModel registerModel, User user) {
		registerModel.setUser(user);
	}

	public void addAddress(RegisterModel registerModel, Address billing) {
		registerModel.setBilling(billing);
	}

	public String saveAll(RegisterModel model) {

		String transactionValue = "success";

		// Fetch User
		User user = model.getUser();
		if (user.getRole().equals("USER")) {
			Cart cart = new Cart();
			cart.setUser(user);
			user.setCart(cart);
		}
		// save the user
		userDAO.addUser(user);
		// get the Address
		Address billing = model.getBilling();
		billing.setUserId(user.getId());
		billing.setBilling(true);
		// save the address
		userDAO.addAddress(billing);

		return transactionValue;
	}

	public String validateUser(User user, MessageContext error) {

		String transitionValue = "success";

		// checking if password matches confirm password
		if (!(user.getPassword().equals(user.getConfirmPassword()))) {
			error.addMessage(new MessageBuilder().error().source("confirmPassword")
					.defaultText("Password does not match Confirm Password").build());
			transitionValue = "failure";
		}

		// check unique email id
		if (userDAO.getUserByEmail(user.getEmail()) != null) {
			error.addMessage(
					new MessageBuilder().error().source("email").defaultText("Email address is already used").build());
			transitionValue = "failure";
		}

		return transitionValue;
	}
}
