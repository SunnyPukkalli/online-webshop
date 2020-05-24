package com.medical.onlineshop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.medical.onlineshop.util.FileUploadUtility;
import com.medical.onlineshop.validator.ProductValidator;
import com.medical.shopbackend.dao.CategoryDAO;
import com.medical.shopbackend.dao.ProductDAO;
import com.medical.shopbackend.dto.Category;
import com.medical.shopbackend.dto.Product;

@Controller
@RequestMapping("/manage")
public class ManagementController {
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private ProductDAO productDAO;

	//Logger
	private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);
	
	@RequestMapping(value="/products",method=RequestMethod.GET)
	public ModelAndView showManageProducts(@RequestParam(name="operation",required=false)String operation){
		
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("userClickManageProduct", true);
		mv.addObject("title", "Manage Products");
		
		
		// New Product
		Product nProduct = new Product();
		nProduct.setSupplierId(1);
		nProduct.setActive(true);
		
		mv.addObject("product", nProduct);
		
		
		
		if(operation !=null){
			if(operation.equals("product")){
				mv.addObject("message", "Product Added Successfully !!!");
			}else if(operation.equals("category")){
				mv.addObject("message", "Category Added Successfully !!!");
			}
		}
		
		return mv;
	}
	
	//Handling Product Submission
	@RequestMapping(value="/products",method=RequestMethod.POST)
	public String handleProductSubmission(@Valid @ModelAttribute("product") Product mProduct, BindingResult results,Model model, HttpServletRequest request){
		
		// mandatory File Upload check
		if(mProduct.getId() == 0 ){
			new ProductValidator().validate(mProduct, results);
		}
		else{
			// edit only when file has been selected
			if(!mProduct.getFile().getOriginalFilename().equals("")){
				new ProductValidator().validate(mProduct, results);
			}
		}
		
		// Check if there are any errors
		if(results.hasErrors()){
			model.addAttribute("userClickManageProduct", true);
			model.addAttribute("title", "Manage Products");
			model.addAttribute("message", "Validation failed for Product Submission");
			return "page";
		}
		
		logger.info(mProduct.toString());
		
		if(mProduct.getId() == 0){
			// create a new Product Record
			productDAO.addProduct(mProduct);
		}else{
			// Update the Product if ID is  not ZERO
			productDAO.updateProduct(mProduct);
		}
		
		if(!mProduct.getFile().getOriginalFilename().equals("")){
			FileUploadUtility.uploadFile(request,mProduct.getFile(),mProduct.getCode());
		}
		
		return "redirect:/manage/products?operation=product";
	}
	
	@RequestMapping(value ="/product/{id}/activation", method =RequestMethod.POST)
	@ResponseBody
	public String handleProductActivation(@PathVariable int id){
	
		// Fetch the Product from Database
		Product product = productDAO.getProduct(id);
		boolean isActive = product.isActive();
		// Activation and De Activation
		product.setActive(!product.isActive());
		//Update the Product
		productDAO.updateProduct(product);
		
		return (isActive)? "You have successfully deactivated the Product :"+product.getId() : "You have successfully activated the Product :"+product.getId();
	}
	
	@RequestMapping(value="/{id}/product", method = RequestMethod.GET)
	public ModelAndView showEditProduct(@PathVariable int id){
		
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("userClickManageProduct", true);
		mv.addObject("title", "Manage Products");
		// Fetch Product from Database
		Product nProduct = productDAO.getProduct(id);
		// set the product fetched from database
		mv.addObject("product", nProduct);
		return mv;
	}
		
	// Handle Category Submission
	@RequestMapping(value="/category", method=RequestMethod.POST )
	public String handleCategorySubmission(@ModelAttribute Category category, BindingResult results, Model model){
		// add the category
		categoryDAO.addCategory(category);
		
		return "redirect:/manage/products?operation=category";
	}
	
	
	@ModelAttribute("categories")
	public List<Category> getCategories(){	
		return categoryDAO.list();
	}
		
	@ModelAttribute("category")
	public Category getCategory(){	
		return new Category();
	}
	
	
}