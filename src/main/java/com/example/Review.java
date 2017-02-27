package com.example;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Review {
	private User user;
	private String content;
	private double rate;
	private Date date;
	
	public Review(User user, String content, double rate, Date date) {
		super();
		this.user = user;
		this.content = content;
		this.rate = rate;
		this.date = new Date();
		//DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		//dateFormat.format(cal);

	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
