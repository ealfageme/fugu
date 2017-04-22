package com.example.Entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.example.Entities.Restaurant.Restaurants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
@Entity
public class Voucher {
	public interface Basic {
	}
	public interface Users {
	}
	public interface Restaurants {
	}
	
	@JsonView(Basic.class)
	private String name;
	@JsonView(Basic.class)
	private String description;
	@JsonView(Basic.class)
	private String expiryDate;
	@JsonView(Basic.class)
	private String min;
	@JsonView(Basic.class)
	private String max;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Basic.class)
	private long id;
	
	@ManyToOne
	@JsonView(Restaurants.class)
	private Restaurant restaurant = new Restaurant();
	@ManyToMany
	@JsonView(Users.class)
	 private List<User> voucherUsers = new ArrayList<>();

	
	public Voucher(){}
	public Voucher(String name, String description, String expiryDate, String min, String max) {
		super();
		this.name = name;
		this.description = description;
		this.expiryDate = expiryDate;
		this.min = min;
		this.max = max;
	}
	
	public String getMin() {
		return min;
	}
	public void setMin(String min) {
		this.min = min;
	}
	public String getMax() {
		return max;
	}
	public void setMax(String max) {
		this.max = max;
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

	public String getExpiryDate() {
		return expiryDate;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	
	
}
