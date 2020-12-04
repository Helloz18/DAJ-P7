package com.nnk.springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.repositories.CurvePointRepository;

@Service
public class CurvePointService {

	@Autowired
	CurvePointRepository curvePointRepo;
	
	public Iterable<CurvePoint> getAllCurvePoint() {
		return curvePointRepo.findAll();
	}
}
