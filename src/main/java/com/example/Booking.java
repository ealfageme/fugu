package com.example;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@ManyToOne
	private User bookingUser = new User();
	@ManyToOne
	private Restaurant bookingRestaurant = new Restaurant();
	private Date date;
	private int number;
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
