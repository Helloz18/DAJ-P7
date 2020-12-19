package com.nnk.springboot.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeController
{
	Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
	@RequestMapping("/")
	public String home(Model model)
	{
		logger.info("home page"); 
		return "home"; 
	}

	@RequestMapping("/admin/home")
	public String adminHome(Model model)
	{
		logger.info("get mapping to /admin/home"); 
		return "redirect:/bidList/list";
	}

	  @GetMapping("/forbidden")
	    public ModelAndView error() {
		  logger.info("get mapping to /forbidden");
	        ModelAndView mav = new ModelAndView();
	        String errorMessage= "You are not authorized for the requested data.";
	        mav.addObject("errorMsg", errorMessage);
	        mav.setViewName("403");
	        return mav;
	    }
}
