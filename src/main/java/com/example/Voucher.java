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
	private String name;
	private String description;
	private Date expiryDate;
	@ManyToOne
	private Restaurant restaurant = new Restaurant();
	@ManyToMany
	 private List<User> voucherUsers = new ArrayList<>();
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	public Voucher(){}
	public Voucher(String name, String description, Date expiryDate) {
		super();
		this.name = name;
		this.description = description;
		this.expiryDate = expiryDate;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	
	
}
