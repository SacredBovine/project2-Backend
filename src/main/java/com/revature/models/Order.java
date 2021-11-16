package com.revature.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Order {

	public Order(int typeOrder, Asset asset, double price, double quantity, User user) {
		super();
		this.typeOrder = typeOrder;
		this.asset = asset;
		this.price = price;
		this.quantity = quantity;
		this.user = user;
	}
	public Order() {
		super();
	}
	public Order(int id, int typeOrder, Asset asset, double price, double quantity, User user) {
		super();
		this.id = id;
		this.typeOrder = typeOrder;
		this.asset = asset;
		this.price = price;
		this.quantity = quantity;
		this.user = user;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private int typeOrder;//1 buy, 2 sell
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(nullable = false, name="asset_id")
	private Asset asset;
	@Column(nullable = false)
	private double price;
	@Column(nullable = false)
	private double quantity;
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.MERGE)
	@JoinColumn(nullable = false, name="user_id")
	@JsonIgnoreProperties("orders")
	private User user;
	public int getId() {
		return id;
	}
	public int getTypeOrder() {
		return typeOrder;
	}
	public Asset getAsset() {
		return asset;
	}
	public double getPrice() {
		return price;
	}
	public double getQuantity() {
		return quantity;
	}
	public User getUser() {
		return user;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setTypeOrder(int typeOrder) {
		this.typeOrder = typeOrder;
	}
	public void setAsset(Asset asset) {
		this.asset = asset;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((asset == null) ? 0 : asset.hashCode());
		result = prime * result + id;
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(quantity);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + typeOrder;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (asset == null) {
			if (other.asset != null)
				return false;
		} else if (!asset.equals(other.asset))
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (Double.doubleToLongBits(quantity) != Double.doubleToLongBits(other.quantity))
			return false;
		if (typeOrder != other.typeOrder)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", typeOrder=" + typeOrder + ", asset=" + asset + ", price=" + price + ", quantity="
				+ quantity + ", user=" + user + "]";
	}
	
	
	
}
