package com.nnk.springboot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;

@Service
public class BidListService {

	@Autowired
	BidListRepository bidListRepo;
	
	public Iterable<BidList> getAllBidList() {
		return bidListRepo.findAll();
	}
	
	public BidList saveBid(BidList bid) {
		return bidListRepo.save(bid);
	}
	
	public Optional<BidList> getById(int id) {
		return bidListRepo.findById(id);
	}
	
	public void delete(BidList bid) {
		bidListRepo.delete(bid);
	}
}
