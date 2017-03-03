package com.example;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Review {

	private String content;
	private double rate;
	private Date date;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@ManyToOne
	private User reviewUser = new User();
	@ManyToOne
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
