package com.example.RestControllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.bind.annotation.RestController;

import com.example.Entities.Menu;
import com.example.Entities.Voucher;
import com.example.Entities.Booking;
import com.example.Entities.City;
import com.example.Entities.Review;
import com.example.Entities.User;
import com.example.Entities.Restaurant;
import com.example.Services.RestaurantService;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantRestController {
	@Autowired
	private RestaurantService restaurantService;

	interface RestaurantDetail extends Restaurant.Basic, City.Basic, Review.Basic, User.Basic, Menu.Basic,
			Voucher.Basic, Booking.Basic, Restaurant.Reviews, Restaurant.Cities, Restaurant.Users, Restaurant.Menus,
			Restaurant.Vouchers, Restaurant.Bookings {
	}

	@ResponseBody
	@JsonView(RestaurantDetail.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Restaurant> getRestaurant(HttpSession session, @PathVariable long id) {
		session.setMaxInactiveInterval(-1);
		Restaurant rest = restaurantService.restaurantServiceFindOne(id);
		if (rest != null) {
			return new ResponseEntity<>(rest, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@ResponseBody
	@JsonView(RestaurantDetail.class)
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public ResponseEntity<Restaurant> putRestaurant(HttpSession session, Authentication authenticate,
			@RequestBody Restaurant updatedRestaurant) {
		session.setMaxInactiveInterval(-1);
		Restaurant rest = restaurantService.restaurantServiceFindByEmail(authenticate.getName());
		if (rest != null) {
			updatedRestaurant.setId(restaurantService.restaurantServiceFindByEmail(authenticate.getName()).getId());
			updatedRestaurant.setPassword(new BCryptPasswordEncoder().encode(rest.getPassword()));
			restaurantService.restaurantServiceSave(updatedRestaurant);
			return new ResponseEntity<>(updatedRestaurant, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}


	@ResponseBody
	@JsonView(RestaurantDetail.class)
	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public ResponseEntity<Restaurant> postRestaurant(HttpSession session, @RequestBody Restaurant rest) {
		session.setMaxInactiveInterval(-1);
		if (restaurantService.restaurantServiceFindByName(rest.getName()) == null) {
			rest.setPassword(new BCryptPasswordEncoder().encode(rest.getPassword()));
			restaurantService.restaurantServiceSave(rest); 
			return new ResponseEntity<>(rest, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}

	@ResponseBody
	@JsonView(Restaurant.Basic.class)
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<Page<Restaurant>> getRestaurants(HttpSession session, Pageable page) {
		session.setMaxInactiveInterval(-1);
		Page<Restaurant> restaurants = restaurantService.restaurantServicefindByRateBetweenOrderByRateDesc(new Double(0.0),
				new Double(5.0), page);
		if (restaurants != null) {
			return new ResponseEntity<>(restaurants, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@ResponseBody
	@JsonView(Menu.Basic.class)
	@RequestMapping(value = "/{id}/menus", method = RequestMethod.GET)
	public ResponseEntity<Page<Menu>> getRestaurantMenus(HttpSession session, @PathVariable long id, Pageable page) {
		session.setMaxInactiveInterval(-1);
		Restaurant restaurant = restaurantService.restaurantServiceFindOne(id);
		if (restaurant != null) {
			return new ResponseEntity<>(restaurantService.restaurantServiceFindByRestaurantMenu(restaurant, page), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@ResponseBody
	@JsonView(Menu.Basic.class)
	@RequestMapping(value = "/{id}/menus", method = RequestMethod.POST)
	public ResponseEntity<Menu> postRestaurantMenus(HttpSession session, @PathVariable long id, Pageable page,
			@RequestBody Menu newMenu) {
		session.setMaxInactiveInterval(-1);
		
		if (restaurantService.restaurantServiceMenuFindDish(newMenu.getDish()) == null) {
			Restaurant restaurant = restaurantService.restaurantServiceFindOne(id);
			restaurant.getMenus().add(newMenu);
			newMenu.setRestaurantMenu(restaurant);
			restaurantService.restaurantServiceMenuSave(newMenu);
			return new ResponseEntity<>(newMenu, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}

	@ResponseBody
	@JsonView(Review.Basic.class)
	@RequestMapping(value = "/{id}/reviews", method = RequestMethod.GET)
	public ResponseEntity<Page<Review>> getRestaurantReviews(HttpSession session, @PathVariable long id,
			Pageable page) {
		session.setMaxInactiveInterval(-1);
		Restaurant restaurant = restaurantService.restaurantServiceFindOne(id);
		if (restaurant != null) {
			return new ResponseEntity<>(restaurantService.reviewRepositoryfindByReviewRestaurant(restaurant, page), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@ResponseBody
	@JsonView(Review.Basic.class)
	@RequestMapping(value = "/{id}/reviews", method = RequestMethod.POST)
	public ResponseEntity<Review> postRestaurantReviews(HttpSession session, @PathVariable long id, Pageable page,
			@RequestBody Review newReview, Authentication authentication) {
		session.setMaxInactiveInterval(-1);
		if (restaurantService.reviewRepositoryfindByContent(newReview.getContent()) == null) {
			Restaurant restaurant = restaurantService.restaurantServiceFindOne(id);
			newReview.setReviewRestaurant(restaurant);
			newReview.setUser((User) authentication.getCredentials());
			restaurantService.reviewRepositorysave(newReview);
			return new ResponseEntity<>(newReview, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}

	@ResponseBody
	@JsonView(Booking.Basic.class)
	@RequestMapping(value = "/{id}/book", method = RequestMethod.POST)
	public ResponseEntity<Booking> postRestaurantBooks(HttpSession session, @PathVariable long id,
			@RequestBody Booking newBooking, Authentication authentication) {
		session.setMaxInactiveInterval(-1);
		if (restaurantService.bookingRepositoryfindBySpecialRequirements(newBooking.getSpecialRequirements()) == null) {
			Restaurant restaurant = restaurantService.restaurantServiceFindOne(id);
			newBooking.setBookingUser(restaurantService.userRepositoryfindByEmail(authentication.getName()));
			newBooking.setBookingRestaurant(restaurant);
			if (restaurant != null) {
				restaurant.getBookings().add(newBooking);
			}
			restaurantService.bookingRepositorysave(newBooking);
			return new ResponseEntity<>(newBooking, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

	}

	@ResponseBody
	@JsonView(Booking.Basic.class)
	@RequestMapping(value = "/{id}/book", method = RequestMethod.GET)
	public ResponseEntity<Page<Booking>> getRestaurantBooks(HttpSession session, @PathVariable long id,
			Authentication authentication, Pageable page) {
		session.setMaxInactiveInterval(-1);
		Restaurant restaurant = restaurantService.restaurantServiceFindOne(id);
		if (restaurant != null) {
			return new ResponseEntity<>(restaurantService.bookingRepositoryfindByBookingRestaurant(restaurant, page), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@ResponseBody
	@JsonView(Voucher.Basic.class)
	@RequestMapping(value = "/{id}/voucher", method = RequestMethod.POST)
	public ResponseEntity<Voucher> postRestaurantVoucher(HttpSession session, @PathVariable long id,
			@RequestBody Voucher newVoucher, Authentication authentication) {
		session.setMaxInactiveInterval(-1);
		if (restaurantService.voucherRepositoryfindByName(newVoucher.getName()) == null) {
			Restaurant restaurant = restaurantService.restaurantServiceFindOne(id);
			newVoucher.setRestaurant(restaurant);
			if (restaurant != null) {
				restaurant.getVouchers().add(newVoucher);
			}
			restaurantService.voucherRepositorysave(newVoucher);
			return new ResponseEntity<>(newVoucher, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}

	@ResponseBody
	@JsonView(Voucher.Basic.class)
	@RequestMapping(value = "/{id}/voucher", method = RequestMethod.GET)
	public ResponseEntity<Page<Voucher>> getRestaurantVoucher(HttpSession session, @PathVariable long id,
			Pageable page) {
		session.setMaxInactiveInterval(-1);
		Restaurant restaurant = restaurantService.restaurantServiceFindOne(id);
		if (restaurant != null) {
			return new ResponseEntity<>(restaurantService.voucherRepositoryfindByRestaurant(restaurant, page), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@ResponseBody
	@JsonView(Restaurant.Basic.class)
	@RequestMapping(value = "/api/restaurants/{id}/unfollow", method = RequestMethod.DELETE)
	public ResponseEntity<List<Restaurant>> deleteUserFollows(HttpServletRequest request, Authentication authentication,
			HttpSession session, @PathVariable long id) {
		session.setMaxInactiveInterval(-1);
		Restaurant restaurant2unfollow = restaurantService.restaurantServiceFindOne(id);
		if (request.isUserInRole("USER")) {
			User userSession = restaurantService.userRepositoryfindByEmail(authentication.getName());
			if (restaurant2unfollow != null) {
				if (userSession.getRestaurants().contains(restaurant2unfollow)) {
					userSession.getRestaurants().remove(restaurant2unfollow);
					restaurantService.userRepositorysave(userSession);
				}
				return new ResponseEntity<>(userSession.getRestaurants(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		return null;
	}

	@ResponseBody
	@JsonView(Restaurant.Basic.class)
	@RequestMapping(value = "/api/restaurants/{id}/follow", method = RequestMethod.POST)
	public ResponseEntity<List<Restaurant>> postUserFollows(HttpServletRequest request, Authentication authentication,
			HttpSession session, @PathVariable long id) {
		session.setMaxInactiveInterval(-1);
		Restaurant restaurant2follow = restaurantService.restaurantServiceFindOne(id);
		if (request.isUserInRole("USER")) {
			User userSession = restaurantService.userRepositoryfindByEmail(authentication.getName());
			if (restaurant2follow != null) {
				userSession.getRestaurants().add(restaurant2follow);
				restaurantService.userRepositorysave(userSession);
				return new ResponseEntity<>(userSession.getRestaurants(), HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		return null;
	}
}
