package com.example.Entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
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
	@JsonIgnore
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
