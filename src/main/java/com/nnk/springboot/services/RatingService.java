package com.nnk.springboot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;

@Service
public class RatingService {

	@Autowired
	RatingRepository ratingRepo;
	
	public Iterable<Rating> getAllRating() {
		return ratingRepo.findAll();
	}
	
	public Rating saveBid(Rating rating) {
		return ratingRepo.save(rating);
	}
	
	public Optional<Rating> getById(int id) {
		return ratingRepo.findById(id);
	}
	
	public void delete(Rating rating) {
		ratingRepo.delete(rating);
	}
}
