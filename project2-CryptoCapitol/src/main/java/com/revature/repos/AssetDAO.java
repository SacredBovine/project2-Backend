package com.revature.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Asset;


public interface AssetDAO extends JpaRepository<Asset, Integer>{
	public Optional<Asset> findBySymbol(String symbol);
}
