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

	public Asset(String name, String symbol, int rank) {
		super();
		this.name = name;
		this.symbol = symbol;
		this.rank = rank;
	}
	public Asset() {
		super();
	}
	public Asset(int id, String name, String symbol, int rank) {
		super();
		this.id = id;
		this.name = name;
		this.symbol = symbol;
		this.rank = rank;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false, length=100)
	private String name;
	@Column(nullable = false, length=4, unique=true)
	private String symbol; 
	@Column(nullable = false)
	private int rank;
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getSymbol() {
		return symbol;
	}
	public int getRank() {
		return rank;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		return "Asset [id=" + id + ", name=" + name + ", symbol=" + symbol + ", rank=" + rank + "]";
	}
	
    

	

	

}
