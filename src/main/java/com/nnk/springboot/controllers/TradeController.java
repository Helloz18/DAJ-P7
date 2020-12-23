package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.TradeService;

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
 * This controller is used to define every path to interact with a Trade.
 * get all
 * add a trade form
 * validate the form
 * show an update form
 * update the trade
 * delete a trade
 * @author hfx28
 *
 */
public class TradeController {
	
	Logger logger = LoggerFactory.getLogger(TradeController.class);
	
	@Autowired
	TradeService tradeService;
	
    @RequestMapping("/trade/list")
    public String home(Model model)
    {
    	logger.info("get mapping to /trade/list");
    	Iterable<Trade> allTrade = tradeService.getAllTrade();
        model.addAttribute("allTrade", allTrade);
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addUser(Trade bid) {
    	logger.info("get mapping to /trade/add");
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
    	logger.info("post mapping to /trade/validate");
    	if(!result.hasErrors()) {
    		trade.setCreationDate(new Timestamp(System.currentTimeMillis()));;
    		tradeService.saveTrade(trade);
    		Iterable<Trade> allTrade = tradeService.getAllTrade();
    		model.addAttribute("allTrade", allTrade);
    		return "redirect:/trade/list";
    	}
        return "trade/add";
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    	logger.info("get mapping to /trade/update/"+id);
    	Trade trade = tradeService.getById(id)
    			.orElseThrow(() -> new IllegalArgumentException("Invalid trade id:"+id));
    	model.addAttribute("trade", trade);
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                             BindingResult result, Model model) {
    	logger.info("post mapping to /trade/update/"+id);
    	if(!result.hasErrors()) {
    		trade.setRevisionDate(new Timestamp(System.currentTimeMillis()));
    		tradeService.saveTrade(trade);
    		return "redirect:/trade/list";
    	}
        return "/trade/update";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
    	logger.info("get mapping to /trade/delete/"+id);
    	Trade trade = tradeService.getById(id)
    			.orElseThrow(() -> new IllegalArgumentException("Invalid trade id:"+id));
    	tradeService.delete(trade);
        return "redirect:/trade/list";
    }
}
