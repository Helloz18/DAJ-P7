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

import java.sql.Timestamp;

import javax.validation.Valid;

@Controller
/**
 * This controller is used to define every path to interact with a CurvePoint.
 * get all
 * add a curvePoint form
 * validate the form
 * show an update form
 * update the curvePoint
 * delete a curvePoint
 * @author hfx28
 *
 */
public class CurveController {
	
	Logger logger = LoggerFactory.getLogger(CurveController.class);

	@Autowired
	private CurvePointService curveService;

    @RequestMapping("/curvePoint/list")
    public String home(Model model)
    {
    	logger.info("get mapping to /curvePoint/list");
    	Iterable<CurvePoint> allCurvePoint = curveService.getAllCurvePoint();
	    model.addAttribute("allCurvePoint", allCurvePoint);
	    return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addCurvePointForm(CurvePoint bid) {
    	logger.info("get mapping to /curvePoint/add");
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
    	logger.info("post mapping to /curvePoint/validate");
    	if(!result.hasErrors()) {
    		curvePoint.setCreationDate(new Timestamp(System.currentTimeMillis()));
    		curveService.saveCurvePoint(curvePoint);
    		Iterable<CurvePoint> allCurvePoint = curveService.getAllCurvePoint();
    		model.addAttribute("allCurvePoint", allCurvePoint);
    		return "redirect:/curvePoint/list";
    	}
        return "curvePoint/add";
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    	logger.info("get mapping to /curvePoint/update/"+id);
    	CurvePoint curvePoint = curveService.getById(id)
    			.orElseThrow(() -> new IllegalArgumentException("Invalid curvePoint Id:" + id));;
    	model.addAttribute("curvePoint", curvePoint);
        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateCurvePoint(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                             BindingResult result, Model model) {
    	logger.info("post mapping to /curvePoint/update/"+id);
    	if(!result.hasErrors()) {
    		curveService.saveCurvePoint(curvePoint);
            return "redirect:/curvePoint/list";
    	}
    	return "curvePoint/update";
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteCurvePoint(@PathVariable("id") Integer id, Model model) {
    	logger.info("get mapping to /curvePoint/delete/"+id);
    	CurvePoint curvePoint = curveService.getById(id)
    			.orElseThrow(() -> new IllegalArgumentException("Invalid curvePoint Id:"+id));
    	curveService.delete(curvePoint);
        return "redirect:/curvePoint/list";
    }
}
