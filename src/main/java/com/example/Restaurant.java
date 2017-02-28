package com.example;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Restaurant {
	//Name, address, rate, menu, vouchers and discounts.
	//Crear clase chef
	//Crear clase reservas
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getWeb() {
		return web;
	}
	public void setWeb(String web) {
		this.web = web;
	}
	public String getFoodTypes() {
		return foodTypes;
	}
	public void setFoodTypes(String foodTypes) {
		this.foodTypes = foodTypes;
	}
	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
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
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public ArrayList<User> getPotentialUsers() {
		return potentialUsers;
	}
	public void setPotentialUsers(ArrayList<User> potentialUsers) {
		this.potentialUsers = potentialUsers;
	}
	public ArrayList<String> getReviews() {
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
	private String name;
	private String address;
	private String web;
	private String foodTypes;
	//Crear clase menu
	private String menu;
	private String email;
	private String description;
	private long phone;
	private String password;
	private ArrayList<User> potentialUsers; 
	private ArrayList<String> reviews; 
	private double userRate;
}
