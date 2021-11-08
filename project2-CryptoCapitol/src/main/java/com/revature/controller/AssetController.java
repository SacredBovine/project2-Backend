package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.service.AssetService;
import com.revature.models.Asset;

@RestController
@RequestMapping(value="/asset")
public class AssetController {
	
	private AssetService assetService;
	
	@Autowired
	public AssetController(AssetService assetService) {
		this.assetService = assetService;
	}
	
	@GetMapping
	public List<Asset> findAllAssets() {
		return assetService.findAllAssets();
	}
	
	@GetMapping("/{id}")
	public Asset oneAsset(@PathVariable("id") int id){
		Asset asset = assetService.findAssetById(id);
		return asset;
	}
	@GetMapping("/{symbol}")
	public Asset oneAsset(@PathVariable("symbol") String symbol){
		Asset asset = assetService.findBySymbol(symbol);
		return asset;
	}
	
	@PostMapping
	@PutMapping
	public ResponseEntity<Asset> addAsset(@RequestBody Asset asset){
		assetService.addOrUpdateAsset(asset);
		return ResponseEntity.status(201).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Asset> deleteAsset(@PathVariable("id") int id){
		assetService.deleteAsset(id);
		return ResponseEntity.status(201).build();
	}
	
	
	
}
