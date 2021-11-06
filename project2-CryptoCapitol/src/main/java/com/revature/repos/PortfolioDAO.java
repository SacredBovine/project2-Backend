package com.revature.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Portfolio;
import com.revature.models.User;

public interface PortfolioDAO extends JpaRepository<Portfolio, Integer>{
	
	public Optional<List<Portfolio>> findByUser(User user);
}
