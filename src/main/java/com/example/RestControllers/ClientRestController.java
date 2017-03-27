package com.example.RestControllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entities.Booking;
import com.example.Entities.Restaurant;
import com.example.Entities.Review;
import com.example.Entities.User;
import com.example.Entities.Voucher;
import com.example.Repositories.UserRepository;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class ClientRestController {

	@Autowired
	private UserRepository userRepository;

	interface UserDetail extends User.Basic,User.Restaurants, User.Reviews, Review.Basic, Voucher.Basic, Booking.Basic, Restaurant.Basic,
	User.Vouchers, User.Bookings{}
	
	@ResponseBody
	@JsonView(UserDetail.class)
	@RequestMapping(value = "/api/clients/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> getClient(HttpSession session, @PathVariable long id) {
		session.setMaxInactiveInterval(-1);
		User user = userRepository.findOne(id);
		if (user != null) {
			return new ResponseEntity<>(userRepository.findOne(id), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@ResponseBody
	@JsonView(UserDetail.class)
	@RequestMapping(value = "/api/clients/", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public User postClient(HttpSession session, @RequestBody User user) {
		session.setMaxInactiveInterval(-1);
		userRepository.save(user);
		return user;
	}

	@ResponseBody
	@JsonView(UserDetail.class)
	@RequestMapping(value = "/api/clients/{id}", method = RequestMethod.PUT)
	public ResponseEntity<User> putUser(HttpSession session, @PathVariable long id, @RequestBody User updatedUser) {
		session.setMaxInactiveInterval(-1);
		
		User user = userRepository.findOne(id);
		if (user != null) {
			updatedUser.setId(id);
			userRepository.save(updatedUser);
			return new ResponseEntity<>(updatedUser, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@ResponseBody
	@JsonView(UserDetail.class)
	@RequestMapping(value = "/api/clients/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(HttpSession session, @PathVariable long id) {
		session.setMaxInactiveInterval(-1);
		userRepository.delete(id);
		return new ResponseEntity<>(null, HttpStatus.OK);

	}

	@ResponseBody
	@JsonView(User.Basic.class)
	@RequestMapping(value = "/api/clients/", method = RequestMethod.GET)
	public ResponseEntity<Page<User>> getClients(HttpSession session, Pageable page) {
		session.setMaxInactiveInterval(-1);
		Page<User> users = userRepository.findAll(page);
		if (users != null) {
			return new ResponseEntity<>(users, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
