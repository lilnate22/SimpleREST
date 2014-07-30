package com.nate.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

import org.codehaus.jackson.map.annotate.JsonRootName;

@Entity
@Table(name = "Items")
@JsonRootName(value = "Items")
public class Items {

	@Column(name="Id")
	@Id
	private int Id;
	@Column(name="userID")
	private double userID;
	
	@Column(name="Name")
	private String Name;
	
	@Column(name="Cost")
	private double Cost;
	
	@Column(name="Price")
	private double Price;
	
	@Column(name="Profit")
	private float Profit;

	public double getUserID() {
		return userID;
	}

	public void setUserID(double userID) {
		this.userID = userID;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public double getCost() {
		return Cost;
	}

	public void setCost(double cost) {
		Cost = cost;
	}

	public double getPrice() {
		return Price;
	}

	public void setPrice(double price) {
		Price = price;
	}

	public float getProfit() {
		return Profit;
	}

	public void setProfit(float profit) {
		Profit = profit;
	}
	
	
	

}
