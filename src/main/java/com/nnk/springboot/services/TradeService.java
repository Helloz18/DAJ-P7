package com.nnk.springboot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;

@Service
public class TradeService {

	@Autowired
	TradeRepository tradeRepo;
	
	public Iterable<Trade> getAllTrade() {
		return tradeRepo.findAll();
	}
	
	public Trade saveTrade(Trade trade) {
		return tradeRepo.save(trade);
	}
	
	public Optional<Trade> getById(Integer id) {
		return tradeRepo.findById(id);
	}
	
	public void delete(Trade trade) {
		tradeRepo.delete(trade);
	}
}
