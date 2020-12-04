package com.nnk.springboot.services;

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
}
