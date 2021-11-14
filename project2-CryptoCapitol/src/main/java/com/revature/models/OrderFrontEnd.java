package com.revature.models;

public class OrderFrontEnd {
	
	public Asset asset;
	public int userId;
	public double quantity;
	
	public OrderFrontEnd(Asset asset, int userId, double quantity) {
		super();
		this.asset = asset;
		this.userId = userId;
		this.quantity = quantity;
	}

	public OrderFrontEnd() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "OrderFrontEnd [asset=" + asset + ", userId=" + userId + ", quantity=" + quantity + "]";
	}
	
	

}
