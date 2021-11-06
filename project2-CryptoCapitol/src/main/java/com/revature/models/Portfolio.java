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

@Entity
public class Portfolio {
	
	public Portfolio() {
		super();
	}
	public Portfolio(Asset asset, User user, double quantity) {
		super();
		this.asset = asset;
		this.user = user;
		this.quantity = quantity;
	}
	public Portfolio(int id, Asset asset, User user, double quantity) {
		super();
		this.id = id;
		this.asset = asset;
		this.user = user;
		this.quantity = quantity;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(nullable = false, name="asset_id")
	private Asset asset;
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(nullable = false, name="user_id")
	private User user;
	@Column(nullable = false)
	private double quantity;
	public int getId() {
		return id;
	}
	public Asset getAsset() {
		return asset;
	}
	public User getUser() {
		return user;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setAsset(Asset asset) {
		this.asset = asset;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "Portfolio [id=" + id + ", asset=" + asset + ", user=" + user + ", quantity=" + quantity + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((asset == null) ? 0 : asset.hashCode());
		result = prime * result + id;
		long temp;
		temp = Double.doubleToLongBits(quantity);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Portfolio other = (Portfolio) obj;
		if (asset == null) {
			if (other.asset != null)
				return false;
		} else if (!asset.equals(other.asset))
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(quantity) != Double.doubleToLongBits(other.quantity))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
	
}
