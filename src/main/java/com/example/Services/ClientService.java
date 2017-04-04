package com.example.Services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.Entities.Booking;
import com.example.Entities.Restaurant;
import com.example.Entities.User;
import com.example.Repositories.BookingRepository;
import com.example.Repositories.RestaurantRepository;
import com.example.Repositories.UserRepository;

@Service
public class ClientService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BookingRepository bookingRepository;
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	public User userRepositoryFindByName(String name){
		return userRepository.findByName(name);
	}
	public User userRepositoryFindOne(Long id){
		return userRepository.findOne(id);
	}
	public User userRepositoryFindByEmail(String email){
		return userRepository.findByEmail(email);
	}
	public Page<User> userRepositoryFindAll(Pageable page){
		return userRepository.findAll(page);
	}
	public Iterable<Restaurant> restaurantRepositoryFindAll(){
		return  restaurantRepository.findAll();
	}
	public void userRepositorysave(User user){
		 userRepository.save(user);
	}
	public List<Booking> bookingRepositoryFindByStateAndBookingUser(String state,User user){
		return bookingRepository.findByStateAndBookingUser(state, user);
	}
	
	public User userRepositoryDeleteUser(User user){
		userRepository.delete(user);
		return user;
	}
}
