package com.example.Entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.Entities.User.Basic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Restaurant {
	public interface Basic {}
	public interface Users {}
	public interface Menus {}
	public interface Vouchers {}
	public interface Cities {}
	public interface Reviews {}
	public interface Bookings {}
	public interface Restaurants {}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Basic.class)
	private long id;
	@JsonView(Basic.class)
	private String name;
	@JsonView(Basic.class)
	private String address;
	@JsonView(Basic.class)
	private String description;
	@JsonView(Basic.class)
	private String email;
	@JsonView(Basic.class)
	private String foodType;
	@JsonView(Basic.class)
	private Double menuPrice;
	@JsonView(Basic.class)
	private boolean breakfast, lunch, dinner;
	@JsonView(Basic.class)
	private String roles;
	@JsonView(Basic.class)
	private Integer phone;
	@JsonView(Basic.class)
	private Double rate;
	@JsonView(Basic.class)
	private String password;
	
	@ManyToMany(mappedBy="restaurants")
	@JsonView(Users.class)
	private List<User> users = new ArrayList<>();
	@OneToMany (mappedBy="restaurantMenu")
	@JsonView(Menus.class)
	private List<Menu> menus = new ArrayList<>();
	@OneToMany(mappedBy="restaurant")
	@JsonView(Restaurants.class)
	private List<Voucher> vouchers = new ArrayList<>();
	@OneToMany(mappedBy="bookingRestaurant")
	@JsonView(Bookings.class)
	private List<Booking> bookings = new ArrayList<>();
	@ManyToOne
	@JsonView(Cities.class)
	private City city;
	@OneToMany(mappedBy="reviewRestaurant")
	@JsonView(Reviews.class)
	private List<Review> restaurantReviews = new ArrayList<>();
	
	public Restaurant (){}
	public Restaurant( String name, String address, String description, String email, String foodTypes,
			Integer phone, double rate, double menuPrice, String password, boolean breakfast, boolean lunch, boolean dinner) {
		super();
		this.name = name;
		this.address = address;
		this.description = description;
		this.email = email;
		this.foodType = foodTypes;
		this.phone = phone;
		this.rate = rate;
		this.roles = "ROLE_RESTAURANT";
		this.menuPrice = menuPrice;
		this.password = new BCryptPasswordEncoder().encode(password);
		this.breakfast=breakfast;
		this.lunch = lunch;
		this.dinner = dinner;
		
	}

	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public Double getMenuPrice() {
		return menuPrice;
	}
	public void setMenuPrice(Double menuPrice) {
		this.menuPrice = menuPrice;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public String getFoodType() {
		return foodType;
	}
	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}
	public List<Booking> getBookings() {
		return bookings;
	}
	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}
	public List<Review> getRestaurantReviews() {
		return restaurantReviews;
	}
	public void setRestaurantReviews(List<Review> restaurantReviews) {
		this.restaurantReviews = restaurantReviews;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public List<Voucher> getVouchers() {
		return vouchers;
	}
	public void setVouchers(List<Voucher> vouchers) {
		this.vouchers = vouchers;
	}
	public List<Menu> getMenus() {
		return menus;
	}
	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFoodTypes() {
		return foodType;
	}
	public void setFoodTypes(String foodTypes) {
		this.foodType = foodTypes;
	}
	public Integer getPhone() {
		return phone;
	}
	public void setPhone(Integer phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public boolean isBreakfast() {
		return breakfast;
	}
	public void setBreakfast(boolean breakfast) {
		this.breakfast = breakfast;
	}
	public boolean isLunch() {
		return lunch;
	}
	public void setLunch(boolean lunch) {
		this.lunch = lunch;
	}
	public boolean isDinner() {
		return dinner;
	}
	public void setDinner(boolean dinner) {
		this.dinner = dinner;
	}
	
}