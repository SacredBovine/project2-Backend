package com.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
@Component
@SessionScope
@Entity
public class Asset {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private int rank;
    @Column(nullable = false, length=3, unique=true)
    private String symbol;
    @Column(nullable = false, length=100)
    private String name;
    private double priceUsd;
    
	public Asset() {
		super();
	}
	public Asset(int rank, String symbol, String name, double priceUsd) {
		super();
		this.rank = rank;
		this.symbol = symbol;
		this.name = name;
		this.priceUsd = priceUsd;
	}
	public Asset(int id, int rank, String symbol, String name, double priceUsd) {
		super();
		this.id = id;
		this.rank = rank;
		this.symbol = symbol;
		this.name = name;
		this.priceUsd = priceUsd;
	}
	
	public int getId() {
		return id;
	}
	public int getRank() {
		return rank;
	}
	public String getSymbol() {
		return symbol;
	}
	public String getName() {
		return name;
	}
	public double getPriceUsd() {
		return priceUsd;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPriceUsd(double priceUsd) {
		this.priceUsd = priceUsd;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(priceUsd);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + rank;
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
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
		Asset other = (Asset) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(priceUsd) != Double.doubleToLongBits(other.priceUsd))
			return false;
		if (rank != other.rank)
			return false;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Asset [id=" + id + ", rank=" + rank + ", symbol=" + symbol + ", name=" + name + ", priceUsd=" + priceUsd
				+ "]";
	}
    	
}
