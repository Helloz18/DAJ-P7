package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RuleNameService;

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
/**
 * This controller is used to define every path to interact with a RuleName.
 * get all
 * add a ruleName form
 * validate the form
 * show an update form
 * update the ruleName
 * delete a ruleName
 * @author hfx28
 *
 */
public class RuleNameController {
	
	Logger logger = LoggerFactory.getLogger(RuleNameController.class);
	
	@Autowired
	private RuleNameService ruleNameService;

    @RequestMapping("/ruleName/list")
    public String home(Model model)
    {
    	logger.info("get mapping to /ruleName/list");
    	Iterable<RuleName> allRuleName = ruleNameService.getAllRuleName();
    	model.addAttribute("allRuleName", allRuleName);        
    	return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName bid) {
    	logger.info("get mapping to /ruleName/add");
        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
    	logger.info("post mapping to /ruleName/validate");
    	if(!result.hasErrors()) {
    		ruleNameService.saveRuleName(ruleName);
    		Iterable<RuleName> allRuleName = ruleNameService.getAllRuleName();
    		model.addAttribute("allRuleName", allRuleName);
    		return "redirect:/ruleName/list";
    	}
    		return "ruleName/add";
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    	logger.info("get mapping to /ruleName/update/"+id);
    	RuleName ruleName = ruleNameService.getById(id)
    			.orElseThrow(() -> new IllegalArgumentException("Invalid ruleName id:"+id));
    	model.addAttribute("ruleName", ruleName);
        return "ruleName/update";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                             BindingResult result, Model model) {
    	logger.info("post mapping to /ruleName/update/"+id);
    	if(!result.hasErrors()) {
    		ruleNameService.saveRuleName(ruleName);
    		Iterable<RuleName> allRuleName = ruleNameService.getAllRuleName();
    		model.addAttribute("allRuleName", allRuleName);
    		return "redirect:/ruleName/list";   	    
    	}
    	return "ruleName/update";
    	}

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
    	logger.info("get mapping /ruleName.delete/"+id);
    	RuleName ruleName = ruleNameService.getById(id)
    			.orElseThrow(() -> new IllegalArgumentException("Invalid ruleName id:"+id));
    	ruleNameService.delete(ruleName);
    	Iterable<RuleName> allRuleName = ruleNameService.getAllRuleName();
    	model.addAttribute("allRuleName", allRuleName);
        return "redirect:/ruleName/list";
    }
}
