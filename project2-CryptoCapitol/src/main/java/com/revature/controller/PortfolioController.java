package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Portfolio;
import com.revature.service.PortfolioService;

@RestController
@RequestMapping(value="/portfolio")
public class PortfolioController {

	private PortfolioService portfolioService;
	
	@Autowired
	public PortfolioController(PortfolioService portfolioService) {
		this.portfolioService = portfolioService;
	}
	
	@GetMapping("/{userid}")
	public List<Portfolio> findPortfolioByUserId(@PathVariable("userid") int userId){
		return portfolioService.findPortfolioByUser(userId);
	}
	
	
}
