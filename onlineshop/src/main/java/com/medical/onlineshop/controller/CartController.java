package com.medical.onlineshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.medical.onlineshop.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;

	@RequestMapping(value = "/show")
	public ModelAndView showCart(@RequestParam(name = "result", required = false) String result) {

		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "User Cart");
		mv.addObject("userClickShowCart", true);

		if (result != null) {

			switch (result) {

			case "added":
				mv.addObject("message", "CartLine added successfully");
				break;

			case "updated":
				mv.addObject("message", "CartLine updated successfully");
				break;

			case "deleted":
				mv.addObject("message", "CartLine removed successfully");
				break;

			case "maximum":
				mv.addObject("message", "CartLine has reached maximum count! ");
				break;

			case "unavailable":
				mv.addObject("message", "Product quantity is not available ");
				break;

			case "error":
				mv.addObject("message", "Something went wrong!");
				break;

			}

		}
		// adding not in video series - starts
		else {
			String response = cartService.validateCartLine();

			if (response.equals("result=modified")) {
				mv.addObject("message", "One or more items inside cart has been modified!");
			}
		}
		// adding not in video series - ends
		mv.addObject("cartLines", cartService.getCartLines());
		return mv;
	}

	@RequestMapping("/{cartLineId}/update")
	public String updateCartLine(@PathVariable int cartLineId, @RequestParam int count) {
		String response = cartService.manageCartLine(cartLineId, count);
		return "redirect:/cart/show?" + response;
	}

	@RequestMapping("/{cartLineId}/delete")
	public String deleteCartLine(@PathVariable int cartLineId) {
		String response = cartService.deleteCartLine(cartLineId);
		return "redirect:/cart/show?" + response;
	}

	@RequestMapping("/add/{productId}/product")
	public String addCart(@PathVariable int productId) {
		String response = cartService.addCartLine(productId);
		return "redirect:/cart/show?" + response;
	}

	// adding not in video series - starts
	/*
	 * after validating it redirect to checkout if result received is success
	 * proceed to checkout else display the message to the user about the
	 * changes in cart page
	 */
	@RequestMapping("/validate")
	public String validateCart() {
		String response = cartService.validateCartLine();
		if (!response.equals("result=success")) {
			return "redirect:/cart/show?" + response;
		} else {
			return "redirect:/cart/checkout";
		}
	}
	// adding not in video series - ends
}
