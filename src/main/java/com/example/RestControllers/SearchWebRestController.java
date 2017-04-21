package com.example.RestControllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.Entities.Restaurant;
import com.example.Repositories.RestaurantRepository;
import com.example.Services.SearchWebService;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/api/search-web")
public class SearchWebRestController {
	@Autowired
	private SearchWebService  searchWebService;
	@ResponseBody
	@JsonView(Restaurant.Basic.class)
	@RequestMapping(value = "/name", method = RequestMethod.GET)
	public ResponseEntity<Restaurant> getRestaurantByName(@RequestParam String name) {
		Restaurant restaurant = searchWebService.serviceRestaurantFindByName(name);
		if (restaurant != null) {
			return new ResponseEntity<>(restaurant, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@ResponseBody
	@JsonView(Restaurant.Basic.class)
	@RequestMapping(value ="/foodtypeandcity", method = RequestMethod.GET)
	public ResponseEntity<List<Restaurant>> getRestaurantByTypeFoodAndCity ( @RequestParam String typeFood,@RequestParam String city){
		List<Restaurant> restaurants = searchWebService.serviceRestaurantFindByFoodTypeAndCityName(typeFood, city);
		return new ResponseEntity<>(restaurants, HttpStatus.OK);
	}
	@ResponseBody
	@JsonView(Restaurant.Basic.class)
	@RequestMapping(value ="/AllParam", method = RequestMethod.GET)
	public ResponseEntity<List<Restaurant>> getRestaurantByParam ( @RequestParam String typeFood,@RequestParam String city,
			@RequestParam Double min,@RequestParam Double max,@RequestParam Double minRate, @RequestParam Double maxRate){
		List<Restaurant> restaurants = searchWebService.serviceRestaurantFindAllParam(typeFood, city,min, max ,minRate,maxRate);
		return new ResponseEntity<>(restaurants, HttpStatus.OK);
	}
	@ResponseBody
	@JsonView(Restaurant.Basic.class)
	@RequestMapping(value ="/AllParamWithoutType", method = RequestMethod.GET)
	public ResponseEntity<List<Restaurant>> getRestaurantByParam2 ( @RequestParam String city,
			@RequestParam Double min,@RequestParam Double max,@RequestParam Double minRate, @RequestParam Double maxRate){
		List<Restaurant> restaurants = searchWebService.serviceRestaurantFindAllParam2(city,min, max ,minRate,maxRate);
		return new ResponseEntity<>(restaurants, HttpStatus.OK);
	}
	@ResponseBody
	@JsonView(Restaurant.Basic.class)
	@RequestMapping(value ="/filters", method = RequestMethod.GET)
	public ResponseEntity<List<Restaurant>> getRestaurantByfilters ( @RequestParam Double min,@RequestParam Double max,
			@RequestParam Double minPrice, @RequestParam Double maxPrice){
		List<Restaurant> restaurants = searchWebService.serviceRestaurantFindByMenuPriceBetweenAndRateBetween(minPrice, maxPrice, minPrice, maxPrice);
		return new ResponseEntity<>(restaurants, HttpStatus.OK);
	}
	
	
}
