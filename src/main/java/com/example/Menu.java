package com.example;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Menu {
	private String dish;
	private Double price;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	public Menu(String name, Double price){
		this.dish = name;
		this.price = price;
	}
	public Menu(){}
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
	
}
