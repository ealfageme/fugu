package com.example.Repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Entities.*;
public interface RestaurantRepository extends JpaRepository<Restaurant,Long>{
	Restaurant findByName(String name);
	Restaurant findByEmail(String email);
	Restaurant findByNameIgnoreCase(String name);
	List<Restaurant> findByMenuPriceBetween(Double minPrice, Double maxPrice);
	List<Restaurant> findByRateBetween(Double min, Double max);
	List<Restaurant> findByCityName(String city);
	List<Restaurant> findByFoodType(String food);
	List<Restaurant> findByFoodTypeAndCityName(String food, String city);
	List<Restaurant> findByMenuPriceGreaterThan(Double minPrice);
	List<Restaurant> findByMenuPriceLessThan(Double maxPrice);
	List<Restaurant> findByRateGreaterThan(Double min);
	List<Restaurant> findByRateLessThan(Double max);
	Page<Restaurant> findByRateBetweenOrderByRateDesc(Double min, Double max, Pageable page);
	//City null
	List<Restaurant> findByMenuPriceBetweenAndRateBetweenAndFoodType(Double minPrice, Double maxPrice, Double min, Double max, String food);
	//Food type null
	List<Restaurant> findByMenuPriceBetweenAndRateBetweenAndCityName(Double minPrice, Double maxPrice, Double min, Double max, String city);
	//Both null
	List<Restaurant> findByMenuPriceBetweenAndRateBetween(Double minPrice, Double maxPrice, Double min, Double max);
	//None is null
	List<Restaurant> findByMenuPriceBetweenAndRateBetweenAndCityNameAndFoodType(Double minPrice, Double maxPrice, Double min, Double max, String city, String food);
}
