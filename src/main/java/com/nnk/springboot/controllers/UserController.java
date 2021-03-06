package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
/**
 * This controller is used to define every path to interact with a User.
 * get all
 * add a user form
 * validate the form
 * show an update form
 * update the user
 * delete a user
 * @author hfx28
 *
 */
public class UserController {
	
	Logger logger = LoggerFactory.getLogger(UserController.class);
	
    @Autowired
    private UserService userService;
    

    @RequestMapping("/user/list")
    public String home(Model model)
    {
    	logger.info("mapping to /user/list");
        model.addAttribute("users", userService.getAllUsers());
        return "user/list";
    }

    @GetMapping("/user/add")
    public String addUser(User user) {
    	logger.info("get mapping to /user/add");
        return "user/add";
    }

    @PostMapping("/user/validate")
    public String validate(@Valid User user, BindingResult result, Model model) {
        logger.info("post mapping to /user/validate");
    	if (!result.hasErrors()) {
    		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    		user.setPassword(encoder.encode(user.getPassword()));
    		user.setEnabled(true);
    		userService.saveUser(user);
    		model.addAttribute("users", userService.getAllUsers());
    		return "redirect:/user/list";
        }
        return "user/add";
    }

    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        logger.info("get mapping to /user/update/"+id);
    	User user = userService.getById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        user.setPassword("");
        model.addAttribute("user", user);
        return "user/update";
    }

    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid User user,
                             BindingResult result, Model model) {
    	logger.info("post mapping to /user/update/"+id);
        if (result.hasErrors()) {
            return "user/update";
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setId(id);
        userService.saveUser(user);
        model.addAttribute("users", userService.getAllUsers());
        return "redirect:/user/list";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
    	logger.info("get mapping to /user/delete/"+id);
        User user = userService.getById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userService.delete(user);
        model.addAttribute("users", userService.getAllUsers());
        return "redirect:/user/list";
    }
}
