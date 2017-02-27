package com.example;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Voucher {
	//Name, description, expiration day and amount
	private String name;
	private String description;
	private Date expiryDate;
	private double amount;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Voucher(String name, String description, Date expiryDate, double amount) {
		super();
		this.name = name;
		this.description = description;
		this.expiryDate = expiryDate;
		this.amount = amount;
	}
	
	
}
