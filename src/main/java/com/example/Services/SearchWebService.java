package com.example.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.Entities.City;
import com.example.Entities.Restaurant;
import com.example.Entities.User;
import com.example.Repositories.CityRepository;
import com.example.Repositories.RestaurantRepository;
import com.example.Repositories.UserRepository;

@Service
public class SearchWebService {
	@Autowired
	RestaurantRepository restaurantRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CityRepository cityRepository;

	public Restaurant serviceRestaurantFindByName(String name) {
		return restaurantRepository.findByName(name);
	}

	public Restaurant serviceRestaurantFindByNameIgnoreCase(String name) {
		return restaurantRepository.findByNameIgnoreCase(name);
	}

	public List<Restaurant> serviceRestaurantFindByFoodTypeAndCityName(String typeFood, String city) {
		return restaurantRepository.findByFoodTypeAndCityName(typeFood, city);
	}

	public List<Restaurant> serviceRestaurantFindByMenuPriceBetweenAndRateBetween(Double minPrice, Double maxPrice,
			Double min, Double max) {
		return restaurantRepository.findByMenuPriceBetweenAndRateBetween(minPrice, maxPrice, min, max);
	}

	public Page<Restaurant> serviceRestaurantFindByRateBetweenOrderByRateDesc(Double min, Double max, Pageable page) {
		return restaurantRepository.findByRateBetweenOrderByRateDesc(min, max, page);
	}

	public City serviceCityFindByName(String name) {
		return cityRepository.findByName(name);
	}

	public void serviceUserSave(User user) {
		userRepository.save(user);
	}

	public void serviceRestaurantSave(Restaurant rest) {
		restaurantRepository.save(rest);
	}

	public User serviceFindByEmailUser(String email) {
		return userRepository.findByEmail(email);
	}

	public Restaurant serviceFindByEmailRestaurant(String email) {
		return restaurantRepository.findByEmail(email);
	}
}
