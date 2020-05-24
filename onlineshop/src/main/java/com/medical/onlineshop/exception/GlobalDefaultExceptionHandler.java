package com.medical.onlineshop.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView handleNoHandlerFoundException(){
		ModelAndView mv = new ModelAndView("error");
		
		mv.addObject("errorTitle"," The page is not yet constructed");
		mv.addObject("errorDescription"," The Page you are looking is not available");
		mv.addObject("title","404 Error page");
		return mv;
	}	
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView handleProductNotFoundException(){
		ModelAndView mv = new ModelAndView("error");
		
		mv.addObject("errorTitle"," The product  is not available");
		mv.addObject("errorDescription"," The Product you are looking is not available");
		mv.addObject("title","Product Not page");
		return mv;
	}
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception ex){
		ModelAndView mv = new ModelAndView("error");
		
		mv.addObject("errorTitle"," Contact Administrator");
		
		/*
		 * Only for Debugging purpose
		 */
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		
		ex.printStackTrace(pw);
		mv.addObject("errorDescription",sw.toString());
		mv.addObject("title","Error");
		return mv;
	}
	
	
}
