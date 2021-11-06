package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Asset;
import com.revature.repos.AssetDAO;

@Service
public class AssetService {
	private AssetDAO assetDAO;
	
	@Autowired
	public AssetService(AssetDAO assetDAO) {
		super();
		this.assetDAO = assetDAO;
	}
	
	public List<Asset> findAllAssets(){
		return assetDAO.findAll();
	}
	
	public Asset findAssetById(int id) {
		return assetDAO.findById(id).get();
	}
	
	public void addOrUpdateAsset(Asset asset) {
		assetDAO.save(asset);
	}
	public void deleteAsset(int id) {
		Asset asset = findAssetById(id);
		assetDAO.delete(asset);
	}
	
}
