package com.example.Entities;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
@Entity
public class Menu {
	interface Basic {
	}

	interface Restaurants {
	}
	
	@JsonView(Basic.class)
	private String dish;
	@JsonView(Basic.class)
	private Double price;
	@JsonView(Basic.class)
	private String description;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Basic.class)
	private long id;
	
	@ManyToOne 
	@JsonIgnore
	private Restaurant restaurantMenu;
	
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
