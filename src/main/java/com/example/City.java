package com.example;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class City {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	//Restaurant List
	
	public City(String name){
		this.name= name;
		
	}
	public City(){}
	
	public void setName(String name){
		this.name= name;
	}
	public String getName(){
		return this.name;
	}
}
