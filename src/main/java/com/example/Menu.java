package com.example;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Menu {
	
	private String dish;
	private Double price;
	private String description;
	@ManyToOne 
	private Restaurant restaurantMenu;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	public Menu(){}
	public Menu(String name, Double price, String description){
		this.dish = name;
		this.price = price;
		this.description= description;
	}
	
	public Restaurant getRestaurantMenu() {
		return restaurantMenu;
	}
	public void setRestaurantMenu(Restaurant menuRestaurant) {
		this.restaurantMenu = menuRestaurant;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDish() {
		return dish;
	}
	public void setDish(String dish) {
		this.dish = dish;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public void setDescription(String description){
		this.description= description;
	}
	public String getDescription(){
		return this.description;
	}
}
