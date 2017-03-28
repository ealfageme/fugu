package com.example.Entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.example.Entities.Restaurant.Restaurants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Booking {
	
	public interface Basic {
	}

	public interface Restaurants {
	}

	public interface Users {
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
	@JsonView(Basic.class)
	private String state;
	public Booking(){}
	public Booking(Date date, int number,String specialRequirements) {
		super();
		this.date = date;
		this.number = number;
		this.specialRequirements=specialRequirements;
		this.state = "In process";
	}

	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
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
