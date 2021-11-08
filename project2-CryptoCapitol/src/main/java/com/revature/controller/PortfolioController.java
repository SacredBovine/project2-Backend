package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Asset;
import com.revature.models.Portfolio;
import com.revature.models.PortfolioDTO;
import com.revature.models.User;
import com.revature.service.AssetService;
import com.revature.service.PortfolioService;
import com.revature.service.UserService;

@RestController
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
	
	@GetMapping("/{userid}")
	public List<Portfolio> findPortfolioByUserId(@PathVariable("userid") int userId){
		return portfolioService.findPortfolioByUser(userId);
	}
	
	@PostMapping
	public ResponseEntity<Asset> addAsset(@RequestBody PortfolioDTO portfolioDTO){
		
		Portfolio portfolio = new Portfolio();
		
		portfolio.setQuantity(portfolioDTO.quantity);
		
		User user = userService.findById(portfolioDTO.userId);
		portfolio.setUser(user);
		
		Asset asset = assetService.findBySymbol(portfolioDTO.assetSymbol);
		portfolio.setAsset(asset);
		
		portfolioService.addOrUpdatePortfolio(portfolio);
		return ResponseEntity.status(201).build();
	}
}
