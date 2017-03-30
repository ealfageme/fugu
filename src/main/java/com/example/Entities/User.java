package com.example.Entities;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.springframework.boot.jackson.JsonComponent;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.NullNode;

import org.springframework.util.Assert;
@JsonComponent
@Entity
public class User{

	public interface Basic {}
	public interface Users {}
	public interface Restaurants {}
	public interface Reviews {}
	public interface Bookings {}
	public interface Vouchers {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Basic.class)
	private long id;
	@JsonView(Basic.class)
	private String name;
	@JsonView(Basic.class)
	private String password;
	@JsonView(Basic.class)
	private String email;
	@JsonView(Basic.class)
	private int age;
	@JsonView(Basic.class)
	private String description;
	@JsonView(Basic.class)
	private String favouriteFood;
	@JsonView(Basic.class)
	private String roles;

	@ManyToMany(fetch = FetchType.EAGER)
	@JsonView(Users.class)
	private List<User> following = new ArrayList<>();
	@ManyToMany
	@JsonView(Restaurants.class)
	private List<Restaurant> restaurants = new ArrayList<>();
	@OneToMany(mappedBy = "reviewUser")
	@JsonView(Reviews.class)
	private List<Review> reviews = new ArrayList<>();
	@OneToMany(mappedBy = "bookingUser")
	@JsonView(Bookings.class)
	private List<Booking> bookings = new ArrayList<>();
	@ManyToMany(mappedBy="voucherUsers")
	@JsonView(Vouchers.class)
	private List<Voucher> userVouchers = new ArrayList<>();

	public User() {}
	public User(String name, String email, String description, String password, int age, String favouriteFood) {
		this.name = name;
		this.email = email;
		this.description = description;
		this.password =  new BCryptPasswordEncoder().encode(password);
		this.age = age;
		this.favouriteFood = favouriteFood;
		this.roles = "ROLE_USER";

	}
	public List<User> getFollowing() {
		return following;
	}
	public void setFollowing(List<User> following) {
		this.following = following;
	}
	public String getFavouriteFood() {
		return favouriteFood;
	}
	public void setFavouriteFood(String favouriteFood) {
		this.favouriteFood = favouriteFood;
	}
	public List<Restaurant> getRestaurants() {
		return restaurants;
	}
	public void setRestaurants(List<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}
	public List<Review> getReviews() {
		return reviews;
	}
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public List<Booking> getBookings() {
		return bookings;
	}
	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}
	public List<Voucher> getUserVouchers() {
		return userVouchers;
	}
	public void setUserVouchers(List<Voucher> userVouchers) {
		this.userVouchers = userVouchers;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public List<Restaurant> getRestaurant() {
		return restaurants;
	}
	public void setRestaurant(ArrayList<Restaurant> Restaurant) {
		this.restaurants = Restaurant;
	}
	/* class UserDeserializer extends JsonDeserializer<User> {
		  @Override
		  public User deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		    ObjectCodec oc = jp.getCodec();
		    JsonNode node = oc.readTree(jp);
		    final Long id = node.get("id").asLong();
		    final String name = node.get("name").asText();
		    final String password = node.get("password").asText();
			final String email = node.get("email").asText();
			final int age = node.get("age").asInt();
			final String description =node.get("description").asText();
			final String favouriteFood = node.get("favouriteFood").asText();
			final String roles = node.get("roles").asText();
		    User user = new User();
		    return new User(name, email, description, password, age, favouriteFood);
		  }
		}*/

}
