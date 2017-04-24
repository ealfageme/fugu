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

import com.example.Entities.Booking;
import com.example.Entities.Restaurant;
import com.example.Entities.Review;
import com.example.Entities.User;
import com.example.Entities.Voucher;
import com.example.Services.ClientService;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/api/clients")
public class ClientRestController {

	interface UserDetail extends User.Basic,User.Restaurants, User.Reviews,
	ReviewDetail, Voucher.Basic, Booking.Basic,
	Restaurant.Basic,User.Vouchers, User.Bookings{}
	interface ReviewDetail extends Review.Basic, Review.Restaurants, Restaurant.Basic {}
	interface BookingDetail extends Booking.Basic, Booking.Restaurants, Restaurant.Basic {}
	interface VoucherDetail extends Voucher.Basic, Voucher.Restaurants, Restaurant.Basic {}
	
	@Autowired
	private ClientService clientService;

	@ResponseBody
	@JsonView (UserDetail.class)
	@RequestMapping (value = "/{name}", method = RequestMethod.GET)
	public ResponseEntity<User> getClient(HttpSession session, @PathVariable String name) {
		session.setMaxInactiveInterval(-1);
		User user = clientService.userRepositoryFindByName(name);
		if (user != null) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@ResponseBody
	@JsonView (User.Basic.class)
	@RequestMapping (value = "/signin", method = RequestMethod.POST)
	public ResponseEntity<User> postClient(HttpSession session, @RequestBody User user) {
		session.setMaxInactiveInterval(-1);
		System.out.println(user.getName());
		if (clientService.userRepositoryFindByName(user.getName()) == null) {
			user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
			clientService.userRepositorysave(user);
			return new ResponseEntity<>(user, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}

	@ResponseBody
	@JsonView (UserDetail.class)
	@RequestMapping (value = "/", method = RequestMethod.PUT)
	public ResponseEntity<User> putUser(HttpSession session, Authentication authenticate, @RequestBody User updatedUser) {
		session.setMaxInactiveInterval(-1);
		User user = clientService.userRepositoryFindByEmail(updatedUser.getEmail());
		if (user != null) {
			if (updatedUser != null) {
				updatedUser.setId(clientService.userRepositoryFindByEmail(updatedUser.getEmail()).getId());
				updatedUser.setFollowing(clientService.userRepositoryFindByEmail(updatedUser.getEmail()).getFollowing());
				updatedUser.setRestaurants(clientService.userRepositoryFindByEmail(updatedUser.getEmail()).getRestaurants());
				updatedUser.setPassword(new BCryptPasswordEncoder().encode(updatedUser.getPassword()));
				clientService.userRepositorysave(updatedUser);
				return new ResponseEntity<>(updatedUser, HttpStatus.OK);
			} else {
				return null;
			}
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@ResponseBody
	@JsonView (User.Basic.class)
	@RequestMapping (value = "/{name}/following", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getUserFollowing(HttpSession session, @PathVariable String name) {
		session.setMaxInactiveInterval(-1);
		User user = clientService.userRepositoryFindByName(name);
		if (user != null) {
			return new ResponseEntity<>(user.getFollowing(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@ResponseBody
	@JsonView (VoucherDetail.class)
	@RequestMapping (value = "/{name}/vouchers", method = RequestMethod.GET)
	public ResponseEntity<List<Voucher>> getUserVoucher(HttpSession session, @PathVariable String name) {
		session.setMaxInactiveInterval(-1);
		User user = clientService.userRepositoryFindByName(name);
		if (user != null) {
			return new ResponseEntity<>(user.getUserVouchers(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@ResponseBody
	@JsonView (BookingDetail.class)
	@RequestMapping (value = "/{name}/book", method = RequestMethod.GET)
	public ResponseEntity<List<Booking>> getUserBooking(HttpSession session, @PathVariable String name) {
		session.setMaxInactiveInterval(-1);
		User user = clientService.userRepositoryFindByName(name);
		if (user != null) {
			return new ResponseEntity<>(user.getBookings(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@ResponseBody
	@JsonView (User.Basic.class)
	@RequestMapping (value = "/{name}/unfollow", method = RequestMethod.DELETE)
	public ResponseEntity<List<User>> deleteUserFollows(HttpServletRequest request, Authentication authentication,
			HttpSession session, @PathVariable String name) {
		
		session.setMaxInactiveInterval(-1);
		User user2follow = clientService.userRepositoryFindByName(name);
		if (request.isUserInRole("USER")) {
			User userSession = clientService.userRepositoryFindByEmail(authentication.getName());
			if (user2follow != null) {
				if (userSession.getFollowing().contains(user2follow)) {
					userSession.getFollowing().remove(user2follow);
					clientService.userRepositorysave(userSession);
				}
				return new ResponseEntity<>(userSession.getFollowing(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		return null;
	}

	@ResponseBody
	@JsonView (User.Basic.class)
	@RequestMapping (value = "/{name}/follow", method = RequestMethod.POST)
	public ResponseEntity<List<User>> postUserFollows(HttpServletRequest request, Authentication authentication,
			HttpSession session, @PathVariable String name) {
		session.setMaxInactiveInterval(-1);
		System.out.println("tambien entro");
		User user2follow = clientService.userRepositoryFindByName(name);
		if (request.isUserInRole("USER")) {
			User userSession = clientService.userRepositoryFindByEmail(authentication.getName());
			if (user2follow != null) {
				userSession.getFollowing().add(user2follow);
				clientService.userRepositorysave(userSession);
				return new ResponseEntity<>(userSession.getFollowing(), HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		return null;
	}
	
	@ResponseBody
	@JsonView(User.Basic.class)
	@RequestMapping(value = "/{name}/isfollowing", method = RequestMethod.GET)
	public ResponseEntity<User> isFollowing(HttpServletRequest request,HttpSession session,Authentication authentication,  @PathVariable String name) {
		session.setMaxInactiveInterval(-1);
		if (request.isUserInRole("USER")) {
			System.out.println(clientService.userRepositoryFindByName(name).getName());
			boolean isfollowing = clientService.userRepositoryFindByEmail(authentication.getName()).getFollowing().contains(clientService.userRepositoryFindByName(name));
			if (isfollowing) {
				System.out.println("lsigue");
				return new ResponseEntity<>(clientService.userRepositoryFindByName(name), HttpStatus.OK);
			} else {
				System.out.println("no le sigue");
				return new ResponseEntity<>(null,HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@ResponseBody
	@JsonView(User.Basic.class)
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<Page<User>> getClients(HttpSession session, Pageable page) {
		session.setMaxInactiveInterval(-1);
		Page<User> users = clientService.userRepositoryFindAll(page);
		if (users != null) {
			return new ResponseEntity<>(users, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
