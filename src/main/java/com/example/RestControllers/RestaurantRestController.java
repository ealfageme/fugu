package com.example.RestControllers;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entities.Menu;
import com.example.Entities.Voucher;
import com.example.Entities.Booking;
import com.example.Entities.City;
import com.example.Entities.Review;
import com.example.Entities.User;
import com.example.Entities.Restaurant;
import com.example.Repositories.BookingRepository;
import com.example.Repositories.MenuRepository;
import com.example.Repositories.RestaurantRepository;
import com.example.Repositories.UserRepository;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class RestaurantRestController {
	@Autowired
	private RestaurantRepository restaurantRepository;
	@Autowired
	private MenuRepository menuRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BookingRepository bookingRepository;

	interface RestaurantDetail extends Restaurant.Basic, City.Basic, Review.Basic, User.Basic, Menu.Basic,
			Voucher.Basic, Booking.Basic, Restaurant.Reviews, Restaurant.Cities, Restaurant.Users, Restaurant.Menus,
			Restaurant.Vouchers, Restaurant.Bookings {
	}

	@ResponseBody
	@JsonView(RestaurantDetail.class)
	@RequestMapping(value = "/api/restaurants/{id}", method = RequestMethod.GET)
	public ResponseEntity<Restaurant> getRestaurant(HttpSession session, @PathVariable long id) {
		session.setMaxInactiveInterval(-1);
		Restaurant rest = restaurantRepository.findOne(id);
		if (rest != null) {
			return new ResponseEntity<>(restaurantRepository.findOne(id), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@ResponseBody
	@JsonView(RestaurantDetail.class)
	@RequestMapping(value = "/api/restaurants/", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Restaurant postRestaurant(HttpSession session, @RequestBody Restaurant rest) {
		session.setMaxInactiveInterval(-1);
		restaurantRepository.save(rest);
		return rest;
	}

	@ResponseBody
	@JsonView(RestaurantDetail.class)
	@RequestMapping(value = "/api/restaurants/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Restaurant> putRestaurant(HttpSession session, @PathVariable long id, @RequestBody Restaurant updatedRestaurant) {
		session.setMaxInactiveInterval(-1);
		
		Restaurant rest = restaurantRepository.findOne(id);
		if (rest != null) {
			updatedRestaurant.setId(id);
			restaurantRepository.save(updatedRestaurant);
			return new ResponseEntity<>(updatedRestaurant, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@ResponseBody
	@JsonView(RestaurantDetail.class)
	@RequestMapping(value = "/api/restaurants/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Restaurant> deleteRestaurant(HttpSession session, @PathVariable long id) {
		session.setMaxInactiveInterval(-1);
		restaurantRepository.delete(id);
		return new ResponseEntity<>(null, HttpStatus.OK);

	}

	@ResponseBody
	@JsonView(Restaurant.Basic.class)
	@RequestMapping(value = "/api/restaurants/", method = RequestMethod.GET)
	public ResponseEntity<Page<Restaurant>> getRestaurants(HttpSession session, Pageable page) {
		session.setMaxInactiveInterval(-1);
		Page<Restaurant> restaurants = restaurantRepository.findByRateBetweenOrderByRateDesc(new Double(0.0),
				new Double(5.0), page);
		if (restaurants != null) {
			return new ResponseEntity<>(restaurants, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@ResponseBody
	@JsonView(Menu.Basic.class)
	@RequestMapping(value = "/api/restaurants/{id}/menus", method = RequestMethod.GET)
	public ResponseEntity<Page<Menu>> getRestaurantMenus(HttpSession session, @PathVariable long id, Pageable page) {
		session.setMaxInactiveInterval(-1);
		Restaurant restaurant = restaurantRepository.findOne(id);
		if (restaurant != null) {
			return new ResponseEntity<>(menuRepository.findByRestaurantMenu(restaurant, page), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	@ResponseBody
	@JsonView(Menu.Basic.class)
	@RequestMapping(value = "/api/restaurants/{id}/menus", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Menu postRestaurantMenus(HttpSession session, @PathVariable long id, Pageable page, Menu newMenu) {
		session.setMaxInactiveInterval(-1);
		Restaurant restaurant = restaurantRepository.findOne(id);
		restaurant.getMenus().add(newMenu);
		return newMenu;
	}
	@ResponseBody
	@JsonView(Booking.Basic.class)
	@RequestMapping(value = "/api/restaurants/{id}/book", method = RequestMethod.POST)
	public Booking postRestaurantBooks(HttpSession session, @PathVariable long id,@RequestBody Booking newBooking, Authentication authentication) {
		session.setMaxInactiveInterval(-1);
		Restaurant restaurant = restaurantRepository.findOne(id);
			newBooking.setBookingUser(userRepository.findByEmail(authentication.getName()));
			newBooking.setBookingRestaurant(restaurant);
			if (restaurant != null) {
				restaurant.getBookings().add(newBooking);
		}
		bookingRepository.save(newBooking);
		return newBooking;
	}
}
