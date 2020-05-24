package com.medical.onlineshop.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.medical.shopbackend.dto.Product;

public class ProductValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Product.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		// Typecast into required class
		Product product = (Product) target;
		
		// whether file has been selected or not
		if(product.getFile() == null || product.getFile().getOriginalFilename().equals("")){
			
			errors.rejectValue("file", null, "Please select an image file to upload!");
			return;
		}
		
		if(!(
				product.getFile().getContentType().equals("image/jpeg") ||
				product.getFile().getContentType().equals("image/png") ||
				product.getFile().getContentType().equals("image/gif")
			  )){
					
					errors.rejectValue("file", null, "Please upload only image file !");
					return;
			
		}
	}

}
