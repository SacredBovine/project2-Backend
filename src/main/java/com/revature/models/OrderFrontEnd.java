package com.revature.models;

public class OrderFrontEnd {
		
	public OrderFrontEnd() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int orderType;
	public String name;
	public String symbol;
	public int rank;
	public int userId;
	public double quantity;
	public double price;	
	
	public OrderFrontEnd(int orderType, String name, String symbol, 
			int rank, int userId, double quantity, double price) {
		super();
		this.orderType = orderType;
		this.name = name;
		this.symbol = symbol;
		this.rank = rank;
		this.userId = userId;
		this.quantity = quantity;
		this.price = price;
	}

}
