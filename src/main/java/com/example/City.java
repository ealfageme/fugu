package com.example;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class City {
	
	private String name;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@OneToMany(mappedBy="city")
	private List<Restaurant> cityResturants = new ArrayList<>();
	
	public List<Restaurant> getCityResturants() {
		return cityResturants;
	}
	public void setCityResturants(List<Restaurant> cityResturants) {
		this.cityResturants = cityResturants;
	}
	public City(){}
	public City(String name){
		this.name= name;
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
