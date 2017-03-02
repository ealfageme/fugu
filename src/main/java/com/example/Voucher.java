package com.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Voucher {
	//Name, description, expiration day and amount
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	private String name;
	private String description;
	private Date expiryDate;
	private double amount;
	@ManyToOne
	private Restaurant restaurant = new Restaurant();
	@ManyToMany
	 private List<User> voucherUsers = new ArrayList<>();
	public String getName() {
		return name;
	}

	public List<User> getVoucherUsers() {
		return voucherUsers;
	}

	public void setVoucherUsers(List<User> voucherUsers) {
		this.voucherUsers = voucherUsers;
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

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
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
