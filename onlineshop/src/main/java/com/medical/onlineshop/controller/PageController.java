package com.medical.onlineshop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.medical.onlineshop.exception.ProductNotFoundException;
import com.medical.shopbackend.dao.CategoryDAO;
import com.medical.shopbackend.dao.ProductDAO;
import com.medical.shopbackend.dto.Category;
import com.medical.shopbackend.dto.Product;

@Controller
public class PageController {

	private static final Logger logger = LoggerFactory.getLogger(PageController.class);

	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private ProductDAO productDAO;

	
	
	@RequestMapping(value = { "/", "/home", "/index" })
	public ModelAndView index(@RequestParam(name="logout",required=false)String logout) {

		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Home");

		logger.info(" INSIDE PAGE CONTROLLER - INDEX METHOD - INFO");
		logger.debug(" INSIDE PAGE CONTROLLER - INDEX METHOD - DEBUG");

		if(logout != null){
			mv.addObject("logout", "User has successfully Logged out");
		}
		
		// passing the list of categories
		mv.addObject("categories", categoryDAO.list());
		mv.addObject("userClickHome", true);

		
		
		return mv;
	}

	@RequestMapping(value = "/about")
	public ModelAndView about() {

		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "About Us");
		mv.addObject("userClickAbout", true);

		return mv;
	}

	@RequestMapping(value = "/contact")
	public ModelAndView contact() {

		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Contact Us");
		mv.addObject("userClickContact", true);

		return mv;
	}

	/*
	 * Method to load all products and based on categories
	 */
	@RequestMapping(value = "/show/all/products")
	public ModelAndView showAllProducts() {

		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "All Products");

		// passing the list of categories
		mv.addObject("categories", categoryDAO.list());

		mv.addObject("userClickAllProducts", true);

		return mv;
	}

	@RequestMapping(value = "/show/category/{id}/products")
	public ModelAndView showCategoryProducts(@PathVariable("id") int id) {

		ModelAndView mv = new ModelAndView("page");

		// categoryDAO to fetch single category
		Category category = null;
		category = categoryDAO.get(id);
		mv.addObject("title", category.getName());
		mv.addObject("userClickCategoryProducts", true);

		// passing the list of categories
		mv.addObject("categories", categoryDAO.list());

		// passing the category also
		mv.addObject("category", category);
		return mv;
	}

	@RequestMapping("/show/{id}/product")
	public ModelAndView showProduct(@PathVariable int id) throws ProductNotFoundException {
		ModelAndView mv = new ModelAndView("page");

		Product product = productDAO.getProduct(id);

		if (product == null){
			throw new ProductNotFoundException();
		}
		
		// update the view count
		product.setViews(product.getViews() + 1);
		productDAO.updateProduct(product);
		// ---------------------------------

		mv.addObject("title", product.getName());
		mv.addObject("product", product);

		mv.addObject("userClickShowProduct", true);

		return mv;
	}

	@RequestMapping(value = "/login")
	public ModelAndView login(@RequestParam(name = "error", required = false) String error ,
			@RequestParam(name = "logout", required = false) String logout) {

		ModelAndView mv = new ModelAndView("login");
		
		if(error != null){
			mv.addObject("message", "Invalid Credentials");
		}
		
		if(logout != null){
			mv.addObject("logout", "User has successfully Logged out");
		}
		
		mv.addObject("title", "Login");
		return mv;
	}
	
	/* Access Denied */
	@RequestMapping(value = "/access-denied")
	public ModelAndView accessDenied(@RequestParam(name = "error", required = false) String error) {

		ModelAndView mv = new ModelAndView("error");	
		mv.addObject("title", "403-Access Denied");
		mv.addObject("errorTitle", " Aha Caught You!");
		mv.addObject("errorDescription", "you are not Authorised to view this page");
		return mv;
	}
	
	/* Logout */
	@RequestMapping(value = "/perform-logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {

		// Fetch authenication
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth != null){
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		
		return "redirect:/login?logout";
	}
}