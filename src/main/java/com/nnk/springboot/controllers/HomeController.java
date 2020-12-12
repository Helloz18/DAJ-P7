package com.nnk.springboot.controllers;

import javax.annotation.security.RolesAllowed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController
{
	Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping("/")//on arrive ici après s'être loggé
	public String home(Model model)
	{
		logger.info("home page"); //si user : /home : invite à se logger ou créer un user alors qu'on est déjà loggé
		return "home"; // cette home page n'est pas logique, un admin peut ajouter un user via user/add quand il veut
	} // la home page générée doit correspondre à la home page d'un USER

	@RolesAllowed("ADMIN")
	@RequestMapping("/admin/home")
	public String adminHome(Model model)
	{
		return "redirect:/bidList/list";//si admin : /admin/home = bid list
	}


}
