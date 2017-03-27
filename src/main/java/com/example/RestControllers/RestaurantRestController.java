package com.example.RestControllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entities.Menu;
import com.example.Entities.City;
import com.example.Entities.Review;
import com.example.Entities.Restaurant;
import com.example.Repositories.MenuRepository;
import com.example.Repositories.RestaurantRepository;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class RestaurantRestController {
	@Autowired
	private RestaurantRepository restaurantRepository;
	@Autowired
	private MenuRepository menuRepository;
	
	interface RestaurantDetail extends Restaurant.Basic, Restaurant.Reviews, Restaurant.Cities, City.Basic, Review.Basic, Restaurant.Users, Restaurant.Menus,
	Restaurant.Vouchers, Restaurant.Bookings{}
	
	@ResponseBody
	@JsonView(RestaurantDetail.class)
	@RequestMapping(value = "/api/restaurants/{id}", method = RequestMethod.GET)
	public ResponseEntity<Restaurant> getRestaurant(HttpSession session, @PathVariable long id) {
		session.setMaxInactiveInterval(-1);
		return new ResponseEntity<>(restaurantRepository.findOne(id), HttpStatus.OK);
	}
	@ResponseBody
	@RequestMapping(value="/api/restaurants/{id}/menus", method=RequestMethod.GET)
	public ResponseEntity<Page<Menu>> getRestaurantMenus(HttpSession session, @PathVariable long id, Pageable page) {
		session.setMaxInactiveInterval(-1);
		Restaurant restaurant=restaurantRepository.findOne(id);
		return new ResponseEntity<>(menuRepository.findByRestaurantMenu(restaurant,page), HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value="/api/restaurants/", method=RequestMethod.GET)
	public ResponseEntity<Page<Restaurant>> getRestaurants(HttpSession session, Pageable page) {
		session.setMaxInactiveInterval(10);

		return new ResponseEntity<>(
				restaurantRepository.findByRateBetweenOrderByRateDesc(new Double(0.0), new Double(5.0), page),
				HttpStatus.OK);
	}
}
