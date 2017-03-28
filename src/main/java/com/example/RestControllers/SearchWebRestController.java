package com.example.RestControllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entities.City;
import com.example.Entities.Restaurant;
import com.example.Repositories.RestaurantRepository;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class SearchWebRestController {
	@Autowired
	private RestaurantRepository  restaurantRepository;
	@ResponseBody
	@JsonView(City.Basic.class)
	@RequestMapping(value = "/api/search-web/", method = RequestMethod.GET)
	public ResponseEntity<Page<Restaurant>> getRestaurants( Pageable page) {
		Page<Restaurant> restaurants = restaurantRepository.findByRateBetweenOrderByRateDesc(new Double(0.0),
				new Double(5.0), page);
		if (restaurants != null) {
			return new ResponseEntity<>(restaurants, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@ResponseBody
	@JsonView(City.Basic.class)
	@RequestMapping(value = "/api/search-web/", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public List<Restaurant> getRestaurantsSearched(Pageable page, @RequestBody Double minValue,@RequestBody Double maxValue,
				@RequestBody Double minRank,@RequestBody Double maxRank) {
		List<Restaurant>restaurants =restaurantRepository.findByMenuPriceBetweenAndRateBetween(minValue, maxValue, minRank, maxRank);
		
		return  restaurants;
	
	}
	
}
