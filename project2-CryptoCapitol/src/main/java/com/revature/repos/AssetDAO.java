package com.revature.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Asset;


public interface AssetDAO extends JpaRepository<Asset, Integer>{
	
}
