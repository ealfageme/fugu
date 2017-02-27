package com.example;

import java.util.ArrayList;

import javax.persistence.Entity;

@Entity
public class Restaurant {
	//Name, address, rate, menu, vouchers and discounts.
	//Crear clase chef
	//Crear clase reservas
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
