package com.nnk.springboot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;

@Service
public class CurvePointService {

	@Autowired
	CurvePointRepository curvePointRepo;
	
	public Iterable<CurvePoint> getAllCurvePoint() {
		return curvePointRepo.findAll();
	}
	
	public CurvePoint saveCurvePoint(CurvePoint curvePoint) {
		return curvePointRepo.save(curvePoint);
	}
	
	public Optional<CurvePoint> getById(Integer id) {
		return curvePointRepo.findById(id);
	}
	
	public void delete(CurvePoint curvePoint) {
		curvePointRepo.delete(curvePoint);
	}
}
