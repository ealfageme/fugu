package com.example.RestControllers;

import java.util.Date;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
import com.example.Services.ClientService;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantRestController {
	@Autowired
	private RestaurantService restaurantService;
	@Autowired
	private ClientService userService;

	interface RestaurantDetail extends Restaurant.Basic, City.Basic, Review.Basic, User.Basic, Menu.Basic,
			Voucher.Basic, Booking.Basic, Restaurant.Reviews, Restaurant.Cities, Restaurant.Users, Restaurant.Menus,
			Restaurant.Vouchers, Restaurant.Bookings {}
	interface ReviewDetail extends Review.Basic, Review.Users, User.Basic {}
	interface BookingDetail extends Booking.Basic, Booking.Users, User.Basic {}

	@ResponseBody
	@JsonView(RestaurantDetail.class)
	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public ResponseEntity<Restaurant> getRestaurant(HttpSession session, @PathVariable String name) {
		session.setMaxInactiveInterval(-1);
		Restaurant rest = restaurantService.restaurantRepositoryFindByName(name);
		if (rest != null) {
			return new ResponseEntity<>(rest, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@ResponseBody
	@JsonView(RestaurantDetail.class)
	@RequestMapping(value = "/city/{city}", method = RequestMethod.GET)
	public ResponseEntity<List<Restaurant>> getRestaurantCity(HttpSession session, @PathVariable String city) {
		session.setMaxInactiveInterval(-1);
		List<Restaurant> rests = restaurantService.restaurantRepositoryFindByCity(city);
		if (rests != null) {
			return new ResponseEntity<>(rests, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@ResponseBody
	@JsonView(RestaurantDetail.class)
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public ResponseEntity<Restaurant> putRestaurant(HttpSession session,
			@RequestBody Restaurant updatedRestaurant) {
		session.setMaxInactiveInterval(-1);
		Restaurant rest = restaurantService.restaurantRepositoryFindByEmail(updatedRestaurant.getEmail());
		if (rest != null) {
			updatedRestaurant.setId(restaurantService.restaurantRepositoryFindByEmail(updatedRestaurant.getEmail()).getId());
			updatedRestaurant.setPassword(new BCryptPasswordEncoder().encode(updatedRestaurant.getPassword()));
			restaurantService.restaurantRepositorySave(updatedRestaurant);
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
		if (restaurantService.restaurantRepositoryFindByName(rest.getName()) == null) {
			rest.setPassword(new BCryptPasswordEncoder().encode(rest.getPassword()));
			restaurantService.restaurantRepositorySave(rest); 
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
		Page<Restaurant> restaurants = restaurantService.restaurantRepositoryfindByRateBetweenOrderByRateDesc(new Double(0.0),
				new Double(5.0), page);
		if (restaurants != null) {
			return new ResponseEntity<>(restaurants, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@ResponseBody
	@JsonView(Menu.Basic.class)
	@RequestMapping(value = "/{name}/menus", method = RequestMethod.GET)
	public ResponseEntity<Page<Menu>> getRestaurantMenus(HttpSession session, @PathVariable String name, Pageable page) {
		session.setMaxInactiveInterval(-1);
		Restaurant restaurant = restaurantService.restaurantRepositoryFindByName(name);
		if (restaurant != null) {
			return new ResponseEntity<>(restaurantService.restaurantServiceFindByRestaurantMenu(restaurant, page), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@ResponseBody
	@JsonView(Menu.Basic.class)
	@RequestMapping(value = "/{name}/menus", method = RequestMethod.POST)
	public ResponseEntity<Menu> postRestaurantMenus(HttpSession session, @PathVariable String name, Pageable page,
			@RequestBody Menu newMenu) {
		session.setMaxInactiveInterval(-1);

		Restaurant restaurant = restaurantService.restaurantRepositoryFindByName(name);
		 if (restaurantService.checkMenu(newMenu,restaurant.getEmail())){
			 restaurantService.saveMenu(newMenu, restaurant.getEmail());	 
			 return new ResponseEntity<>(newMenu, HttpStatus.CREATED);
		 } else {
			 return new ResponseEntity<>(HttpStatus.CONFLICT);
		 }


	}

	@ResponseBody
	@JsonView(ReviewDetail.class)
	@RequestMapping(value = "/{name}/reviews", method = RequestMethod.GET)
	public ResponseEntity<Page<Review>> getRestaurantReviews(HttpSession session, @PathVariable String name,
			Pageable page) {
		session.setMaxInactiveInterval(-1);
		Restaurant restaurant = restaurantService.restaurantRepositoryFindByName(name);
		if (restaurant != null) {
			return new ResponseEntity<>(restaurantService.reviewRepositoryfindByReviewRestaurant(restaurant, page), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@ResponseBody
	@JsonView(Review.Basic.class)
	@RequestMapping(value = "/{name}/reviews", method = RequestMethod.POST)
	public ResponseEntity<Review> postRestaurantReviews(HttpSession session, @PathVariable String name, Pageable page,
			@RequestBody Review newReview, Authentication authentication) {
		session.setMaxInactiveInterval(-1);
		if (restaurantService.reviewRepositoryfindByContent(newReview.getContent()) == null) {
			Restaurant restaurant = restaurantService.restaurantRepositoryFindByName(name);
			newReview.setReviewRestaurant(restaurant);
			newReview.setUser(userService.userRepositoryFindByEmail(authentication.getName()));
			restaurantService.reviewRepositorysave(newReview);
			return new ResponseEntity<>(newReview, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}

	@ResponseBody
	@JsonView(Booking.Basic.class)
	@RequestMapping(value = "/{name}/book", method = RequestMethod.POST)
	public ResponseEntity<Booking> postRestaurantBooks(HttpSession session, @PathVariable String name,
			Authentication authentication,@RequestBody Booking newBooking) {
		session.setMaxInactiveInterval(-1);
		if (restaurantService.bookingRepositoryfindById(newBooking.getId()) == null) {
			Restaurant restaurant = restaurantService.restaurantRepositoryFindByName(name);
			newBooking.setBookingRestaurant(restaurant);
			newBooking.setBookingUser(userService.userRepositoryFindByEmail(authentication.getName()));
				restaurant.getBookings().add(newBooking);

			restaurantService.bookingRepositorysave(newBooking);
			return new ResponseEntity<>(newBooking, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

	}
		

	@ResponseBody
	@JsonView(BookingDetail.class)
	@RequestMapping(value = "/{name}/book", method = RequestMethod.GET)
	public ResponseEntity<Page<Booking>> getRestaurantBooks(HttpSession session, @PathVariable String name,
			Authentication authentication, Pageable page) {
		session.setMaxInactiveInterval(-1);
		Restaurant restaurant = restaurantService.restaurantRepositoryFindByName(name);
		if (restaurant != null) {
			return new ResponseEntity<>(restaurantService.bookingRepositoryfindByBookingRestaurant(restaurant, page), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@ResponseBody
	@JsonView(Voucher.Basic.class)
	@RequestMapping(value = "/{name}/voucher", method = RequestMethod.POST)
	public ResponseEntity<Voucher> postRestaurantVoucher(HttpSession session, @PathVariable String name,
			@RequestBody Voucher newVoucher, Authentication authentication) {
		session.setMaxInactiveInterval(-1);
		if (restaurantService.voucherRepositoryfindByName(newVoucher.getName()) == null) {
			System.out.println(name);
			Restaurant restaurant = restaurantService.restaurantRepositoryFindByName(name);
			newVoucher.setRestaurant(restaurant);
			if (restaurant != null) {
				restaurant.getVouchers().add(newVoucher);
			}
			newVoucher.setVoucherUsers(userService.userRepositoryFindByAgeBetween(Integer.parseInt(newVoucher.getMin()), Integer.parseInt(newVoucher.getMax())));
			restaurantService.voucherRepositorysave(newVoucher);
			return new ResponseEntity<>(newVoucher, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}

	@ResponseBody
	@JsonView(Voucher.Basic.class)
	@RequestMapping(value = "/{name}/voucher", method = RequestMethod.GET)
	public ResponseEntity<Page<Voucher>> getRestaurantVoucher(HttpSession session, @PathVariable String name,
			Pageable page) {
		session.setMaxInactiveInterval(-1);
		Restaurant restaurant = restaurantService.restaurantRepositoryFindByName(name);
		if (restaurant != null) {
			return new ResponseEntity<>(restaurantService.voucherRepositoryfindByRestaurant(restaurant, page), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@ResponseBody
	@JsonView(Restaurant.Basic.class)
	@RequestMapping(value = "/{name}/unfollow", method = RequestMethod.DELETE)
	public ResponseEntity<List<Restaurant>> deleteUserFollows(HttpServletRequest request, Authentication authentication,
			HttpSession session, @PathVariable String name) {
		session.setMaxInactiveInterval(-1);
		Restaurant restaurant2unfollow = restaurantService.restaurantRepositoryFindByName(name);
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
	@RequestMapping(value = "/{name}/follow", method = RequestMethod.POST)
	public ResponseEntity<List<Restaurant>> postUserFollows(HttpServletRequest request, Authentication authentication,
			HttpSession session, @PathVariable String name) {
		session.setMaxInactiveInterval(-1);
		System.out.println("entra");
		Restaurant restaurant2follow = restaurantService.restaurantRepositoryFindByName(name);
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
