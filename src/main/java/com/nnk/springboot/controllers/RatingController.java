package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class RatingController {
	
	Logger logger = LoggerFactory.getLogger(RatingController.class);
	
	@Autowired
	RatingService ratingService;

    @RequestMapping("/rating/list")
    public String home(Model model)
    {
    	logger.info("get mapping to /rating/list");
    	Iterable<Rating> allRating = ratingService.getAllRating();
    	model.addAttribute("allRating", allRating);
        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {
    	logger.info("get mapping to /rating/add");
        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
    	logger.info("post mapping to /rating/validate");
    	if(!result.hasErrors()) {
    		ratingService.saveRating(rating);
    		Iterable<Rating> allRating = ratingService.getAllRating();
    		model.addAttribute("allRating", allRating);
    		return "redirect:/rating/list";
    	}    	
    	return "rating/add";   
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    	logger.info("get mapping to /rating/update/"+id);
    	Rating rating = ratingService.getById(id)
    			.orElseThrow(() -> new IllegalArgumentException("Invalid id:"+id));
    	model.addAttribute("rating", rating);
        return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                             BindingResult result, Model model) {
    	logger.info("post mapping to /rating/update/"+id);
    	if(!result.hasErrors()) {
    		ratingService.saveRating(rating);
    		Iterable<Rating> allRating = ratingService.getAllRating();
    		model.addAttribute("allRating", allRating);
            return "redirect:/rating/list";
    	}
    	return "rating/update";
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
    	logger.info("get mapping to /rating/delete/"+id);
    	Rating rating = ratingService.getById(id)
    			.orElseThrow(() -> new IllegalArgumentException("Ivalid id:"+id));
    	ratingService.delete(rating);
        return "redirect:/rating/list";
    }
}
