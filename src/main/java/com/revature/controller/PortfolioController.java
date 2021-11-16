package com.revature.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.SessionScope;


import com.revature.models.Asset;
import com.revature.models.Portfolio;
import com.revature.models.User;
import com.revature.service.AssetService;
import com.revature.service.PortfolioService;
import com.revature.service.UserService;

@RestController

@CrossOrigin(origins ="http://localhost:4200", allowCredentials = "true")
@SessionScope
@RequestMapping(value="/portfolio")
public class PortfolioController {

	private PortfolioService portfolioService;
	private UserService userService;
	private AssetService assetService;
	
	@Autowired
	public PortfolioController(PortfolioService portfolioService, UserService userService, AssetService assetService) {
		this.portfolioService = portfolioService;
		this.userService = userService;
		this.assetService = assetService;
	}
	
	@GetMapping
	public List<Portfolio> findAllPortfolios() {
		return portfolioService.findAllPortfolio();
	}
	
	@GetMapping("/{userid}")
	public List<Portfolio> findPortfolioByUserId(@PathVariable("userid") int userId){
		return portfolioService.findPortfolioByUser(userId);
	}
	
	@PostMapping
	public ResponseEntity<List<Portfolio>> addPortfolio(@RequestBody Portfolio Portfolio){
		System.out.println(Portfolio.toString());
		portfolioService.addOrUpdatePortfolio(Portfolio);
		return ResponseEntity.status(200).body(portfolioService.findAllPortfolio());
	}
	
	@PutMapping
	public ResponseEntity<List<Portfolio>> updatePortfolio(@RequestBody Portfolio Portfolio){
		System.out.println(Portfolio.toString());
		portfolioService.addOrUpdatePortfolio(Portfolio);
		return ResponseEntity.status(201).body(portfolioService.findAllPortfolio());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Portfolio> deletePortfolio(@PathVariable("id") int id){
		portfolioService.deletePortfolio(id);
		return ResponseEntity.status(201).build();
	}

}
