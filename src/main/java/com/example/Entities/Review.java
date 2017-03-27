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
public class Review {
	public interface Basic {
	}

	public interface Users {
	}
	public interface Restaurants {
	}
	
	@JsonView(Basic.class)
	private String content;
	@JsonView(Basic.class)
	private double rate;
	@JsonView(Basic.class)
	private Date date;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Basic.class)
	private long id;
	
	@ManyToOne
	@JsonView(Users.class)
	private User reviewUser = new User();
	@ManyToOne
	@JsonView(Restaurants.class)
	private Restaurant reviewRestaurant = new Restaurant();
	
	public Review(){}
	public Review(String content, double rate, Date date) {
		super();
		this.content = content;
		this.rate = rate;
		this.date = new Date();
		//DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		//dateFormat.format(cal);

	}
	public User getReviewUser() {
		return reviewUser;
	}
	public void setReviewUser(User reviewUser) {
		this.reviewUser = reviewUser;
	}
	public Restaurant getReviewRestaurant() {
		return reviewRestaurant;
	}
	public void setReviewRestaurant(Restaurant reviewRestaurant) {
		this.reviewRestaurant = reviewRestaurant;
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public User getUser() {
		return reviewUser;//user;
	}

	public void setUser(User reviewUser) {
		this.reviewUser = reviewUser;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}	
}
