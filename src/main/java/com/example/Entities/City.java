package com.example.Entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.example.Entities.Booking.Users;
import com.fasterxml.jackson.annotation.JsonView;
import com.example.Entities.*;
import com.example.Repositories.*;
import com.example.Security.*;
import com.example.Controllers.*;
@Entity
public class City {

	public interface Basic {
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
