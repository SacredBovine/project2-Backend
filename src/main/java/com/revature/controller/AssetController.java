package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.SessionScope;
import com.revature.service.AssetService;
import com.revature.models.Asset;

@RestController
@SessionScope
@RequestMapping(value="/asset")
@CrossOrigin(origins ="http:172.17.14.49:4200")
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
		Asset asset = assetService.findBySymbol(symbol).get();
		return asset;
	}
	
	@PostMapping
	public ResponseEntity<List<Asset>> addAsset(@RequestBody Asset asset){
		System.out.println(asset.toString());
		assetService.addOrUpdateAsset(asset);
		return ResponseEntity.status(200).body(assetService.findAllAssets());
	}
	
	@PutMapping
	public ResponseEntity<List<Asset>> updateAsset(@RequestBody Asset asset){
		System.out.println(asset.toString());
		assetService.addOrUpdateAsset(asset);
		return ResponseEntity.status(201).body(assetService.findAllAssets());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Asset> deleteAsset(@PathVariable("id") int id){
		assetService.deleteAsset(id);
		return ResponseEntity.status(201).build();
	}
	
}
