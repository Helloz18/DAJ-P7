package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurvePointService;

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
public class CurveController {
	
	Logger logger = LoggerFactory.getLogger(CurveController.class);

	@Autowired
	private CurvePointService curveService;

    @RequestMapping("/curvePoint/list")
    public String home(Model model)
    {
    	logger.info("mapping to /curvePoint/list");
    	Iterable<CurvePoint> allCurvePoint = curveService.getAllCurvePoint();
	    model.addAttribute("allCurvePoint", allCurvePoint);
	    return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addBidForm(CurvePoint bid) {
    	logger.info("mapping to /curvePoint/add");
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
    	logger.info("mapping to /curvePoint/validate");
    	if(!result.hasErrors()) {
    		curveService.saveCurvePoint(curvePoint);
    		Iterable<CurvePoint> allCurvePoint = curveService.getAllCurvePoint();
    		model.addAttribute("allCurvePoint", allCurvePoint);
    	}
    	else {
    		return "curvePoint/add";
    	}
        return "curvePoint/add";
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    	logger.info("mapping to /curvePoint/update/"+id);
    	CurvePoint curvePoint = curveService.getById(id)
    			.orElseThrow(() -> new IllegalArgumentException("Invalid curvePoint Id:" + id));;
    	model.addAttribute("curvePoint", curvePoint);
        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                             BindingResult result, Model model) {
    	logger.info("mapping to /curvePoint/update/"+id);
    	if(!result.hasErrors()) {
    		curveService.saveCurvePoint(curvePoint);
    	}
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
    	logger.info("mapping to /curvePoint/delete/"+id);
    	CurvePoint curvePoint = curveService.getById(id)
    			.orElseThrow(() -> new IllegalArgumentException("Invalid curvePoint Id:"+id));
    	curveService.delete(curvePoint);
        return "redirect:/curvePoint/list";
    }
}
