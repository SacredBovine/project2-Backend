package com.revature.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Portfolio;
import com.revature.models.User;
import com.revature.repos.PortfolioDAO;

@Service
public class PortfolioService {
	
	
	private PortfolioDAO portfolioDAO;
	private UserService userService;
	
	@Autowired
	public PortfolioService(PortfolioDAO portfolioDAO, UserService userService) {
		super();
		this.portfolioDAO = portfolioDAO;
		this.userService = userService;
	}
	
	
	public Portfolio findById(int id) {
		return portfolioDAO.findById(id).get();
	}
	
	
	public List<Portfolio> findPortfolioByUser(int userId) {
		User user = userService.findById(userId);
		
		Optional<List<Portfolio>> oList = portfolioDAO.findByUser(user);
		if(oList.isPresent()) {
			return oList.get();
		}
		return new ArrayList<Portfolio>();
		
	}
	
	public void addOrUpdatePortfolio(Portfolio portfolio) {
		portfolioDAO.save(portfolio);
	}
	
	public void deletePortfolio(int id) {
		Portfolio portfolio = findById(id);
		portfolioDAO.delete(portfolio);
	}
	
}
