package com.example;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class User {
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
	private String email;
	private String description;
	private String password;
	private int age;
	/*private List<String> preferences; 
	/*private List<String> starredRestaurants; 
	private List<String> reviews; 
	private double userRate;
	
	public User(String name, String email, String description, String password, int age, ArrayList<String> preferences,
			ArrayList<String> starredRestaurants, ArrayList<String> reviews, double userRate) {
		super();
		this.name = name;
		this.email = email;
		this.description = description;
		this.password = password;
		this.age = age;
		this.preferences = preferences;
		this.starredRestaurants = starredRestaurants;
		this.reviews = reviews;
		this.userRate = userRate;
	}*/
	public User(String name,String email,String description, String password,int age){
		this.name=name;
		this.email=email;
		this.description= description;
		this.password= password;
		this.age = age;

		
	}
	public User(){}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	/*
	public List<String> getPreferences() {
		return preferences;
	}

	public void setPreferences(List<String> preferences) {
		this.preferences = preferences;
	}

	/*public List<String> getStarredRestaurants() {
		return starredRestaurants;
	}

	public void setStarredRestaurants(ArrayList<String> starredRestaurants) {
		this.starredRestaurants = starredRestaurants;
	}

	public List<String> getReviews() {
		return reviews;
	}

	public void setReviews(ArrayList<String> reviews) {
		this.reviews = reviews;
	}

	public double getUserRate() {
		return userRate;
	}

	public void setUserRate(double userRate) {
		this.userRate = userRate;
	}
	*/
	
}
