package com.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonView;


@Entity
public class Booking {
	
	interface Basic {
	}

	interface Restaurants {
	}

	interface Users {
	}

	@ManyToOne
	@JsonView(Users.class)
	private User bookingUser = new User();
	@ManyToOne
	@JsonView(Restaurants.class)
	private Restaurant bookingRestaurant = new Restaurant();
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Basic.class)
	private long id;
	@JsonView(Basic.class)
	private Date date;
	@JsonView(Basic.class)
	private int number;
	@JsonView(Basic.class)
	private String specialRequirements;
	
	public Booking(){}
	public Booking(Date date, int number,String specialRequirements) {
		super();
		//this.restaurant = restaurant;
		this.date = date;
		//this.user = user;
		this.number = number;
		this.specialRequirements=specialRequirements;
	}

	public User getBookingUser() {
		return bookingUser;
	}
	public void setBookingUser(User bookingUser) {
		this.bookingUser = bookingUser;
	}
	public Restaurant getBookingRestaurant() {
		return bookingRestaurant;
	}
	public void setBookingRestaurant(Restaurant bookingRestaurant) {
		this.bookingRestaurant = bookingRestaurant;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Restaurant getRestaurant() {
		return null;//restaurant;
	}
	public void setRestaurant(Restaurant restaurant) {
		//this.restaurant = restaurant;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getSpecialRequirements() {
		return specialRequirements;
	}
	public void setSpecialRequirements(String specialRequirements) {
		this.specialRequirements = specialRequirements;
	}
	
}
