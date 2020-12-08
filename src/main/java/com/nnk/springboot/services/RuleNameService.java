package com.nnk.springboot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;

@Service
public class RuleNameService {

	@Autowired
	RuleNameRepository ruleNameRepo;

	public Iterable<RuleName> getAllRuleName() {
		return ruleNameRepo.findAll();
	}

	public RuleName saveRuleName(RuleName ruleName) {
		return ruleNameRepo.save(ruleName);
	}

	public Optional<RuleName> getById(int id) {
		return ruleNameRepo.findById(id);
	}

	public void delete(RuleName ruleName) {
		ruleNameRepo.delete(ruleName);
	}
}
