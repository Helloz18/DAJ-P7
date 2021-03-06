package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;

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
 * This controller is used to define every path to interact with a BidList.
 * get all
 * add a bidList form
 * validate the form
 * show an update form
 * update the bidList
 * delete a bidList
 * @author hfx28
 *
 */
public class BidListController {
	
	Logger logger = LoggerFactory.getLogger(BidListController.class);

	@Autowired
	private BidListService bidListService;

    @RequestMapping("/bidList/list")
    public String home(Model model)
    {
    	logger.info("mapping to /bidList/list");
    	Iterable<BidList> allBidList = bidListService.getAllBidList();
	    model.addAttribute("allBidList", allBidList);
	    return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
    	logger.info("get mapping to /bidList/add");
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
    	logger.info("post mapping to /bidList/validate");
    	if (!result.hasErrors()) {
    		bid.setCreationDate(new Timestamp(System.currentTimeMillis()));
            bidListService.saveBid(bid);           
            Iterable<BidList> allBidList = bidListService.getAllBidList();
            model.addAttribute("allBidList", allBidList);
            return "redirect:/bidList/list";
    	}
    	return "bidList/add";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    	logger.info("get mapping to /bidList/update/"+id);
    	BidList bid = bidListService.getById(id)
    			.orElseThrow(() -> new IllegalArgumentException("Invalid bidList Id:" + id));
    	model.addAttribute("bidList", bid);
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                             BindingResult result, Model model) {
    	logger.info("post mapping to /bidList/update/"+id);
    	if(!result.hasErrors()) {
    		bidList.setRevisionDate(new Timestamp(System.currentTimeMillis()));
    		bidListService.saveBid(bidList);
            return "redirect:/bidList/list";
    	}
		return "bidList/update";    	
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
    	logger.info("get mapping to /bidList/delete/"+id);
    	BidList bid = bidListService.getById(id)
    			.orElseThrow(() -> new IllegalArgumentException("Invalid bidList Id:" + id));
    	bidListService.delete(bid);
        return "redirect:/bidList/list";
    }
}
