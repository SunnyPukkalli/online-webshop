package com.medical.onlineshop.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.medical.onlineshop.model.UserModel;
import com.medical.shopbackend.dao.UserDAO;
import com.medical.shopbackend.dto.User;

@ControllerAdvice
public class GlobalController {

	@Autowired
	private HttpSession session;

	@Autowired
	private UserDAO userDAO;

	private UserModel userModel = null;

	private User user = null;

	@ModelAttribute("userModel")
	public UserModel getUserModel() {

		if (session.getAttribute("userModel") == null) { // add the user model

			// get the authentication object
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

			if (!authentication.getPrincipal().equals("anonymousUser")) {
				// get the user from the database
				user = userDAO.getUserByEmail(authentication.getName());

				if (user != null) {
					// create new usermodel object
					userModel = new UserModel();
					userModel.setId(user.getId());
					userModel.setEmail(user.getEmail());
					userModel.setFullName(user.getFirstName() + " " + user.getLastName());
					userModel.setRole(user.getRole());

					if (user.getRole().equals("USER")) {
						// set the cart if only buyer
						userModel.setCart(user.getCart());
					}

					session.setAttribute("userModel", userModel);
					return userModel;
				}
			}

		}
		return (UserModel) session.getAttribute("userModel");
	}

}
