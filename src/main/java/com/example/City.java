package com.example;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.example.Booking.Users;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class City {

	interface Basic {
	}

	interface Restaurants {
	}
	@JsonView(Basic.class)
	private String name;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Basic.class)
	private long id;
	
	@OneToMany(mappedBy="city")
	@JsonView(Restaurants.class)
	private List<Restaurant> cityResturants = new ArrayList<>();
	
	
	public City(){}
	public City(String name){
		this.name= name;
	}
	
	public List<Restaurant> getCityResturants() {
		return cityResturants;
	}
	public void setCityResturants(List<Restaurant> cityResturants) {
		this.cityResturants = cityResturants;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}	
	public void setName(String name){
		this.name= name;
	}
	public String getName(){
		return this.name;
	}
}
