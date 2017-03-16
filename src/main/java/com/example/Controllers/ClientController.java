package com.example.Controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.Entities.User;
import com.example.Repositories.RestaurantRepository;
import com.example.Repositories.UserRepository;
import com.fasterxml.jackson.annotation.JsonView;

@Controller
public class ClientController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RestaurantRepository restaurantRepository;

	@JsonView(User.Basic.class)
	@RequestMapping("/public-client/{name}")
	public String publicClient(Model model, HttpServletRequest request, Authentication authentication,
			@PathVariable String name, @RequestParam(required = false) String followPulsed,
			@RequestParam(required = false) String unfollowPulsed) {

		model.addAttribute("user", userRepository.findByName(name));
		model.addAttribute("restaurants", userRepository.findByName(name).getRestaurants());
		model.addAttribute("following", userRepository.findByName(name).getFollowing());
		model.addAttribute("bookings", userRepository.findByName(name).getBookings());
		model.addAttribute("vouchers", userRepository.findByName(name).getUserVouchers());
		model.addAttribute("reviews", userRepository.findByName(name).getReviews());
		model.addAttribute("generalRestaurants", restaurantRepository.findAll());
		model.addAttribute("inSession", (request.isUserInRole("USER") || request.isUserInRole("RESTAURANT")));
		String fileName = "profileImage" + userRepository.findByName(name).getId() + ".jpg";
		model.addAttribute("fileName", fileName);
		/*
		 * Following feature has to be implemented
		 */
		if (request.isUserInRole("USER")) {
			String userloggin = authentication.getName();
			model.addAttribute("followButton",
					!userRepository.findByEmail(userloggin).getFollowing().contains(userRepository.findByName(name)));
			model.addAttribute("unfollowButton",
					userRepository.findByEmail(userloggin).getFollowing().contains(userRepository.findByName(name)));

			if (followPulsed != null) {
				System.out.println("Entra follow");
				userRepository.findByEmail(userloggin).getFollowing().add(userRepository.findByName(name));
				userRepository.save(userRepository.findByEmail(userloggin));
			}

			model.addAttribute("followButton",
					!userRepository.findByEmail(userloggin).getFollowing().contains(userRepository.findByName(name)));
			model.addAttribute("unfollowButton",
					userRepository.findByEmail(userloggin).getFollowing().contains(userRepository.findByName(name)));

			if (unfollowPulsed != null) {
				System.out.println("Entra unfollow");
				userRepository.findByEmail(userloggin).getFollowing().remove(userRepository.findByName(name));
				userRepository.save(userRepository.findByEmail(userloggin));
			}

			model.addAttribute("followButton",
					!userRepository.findByEmail(userloggin).getFollowing().contains(userRepository.findByName(name)));
			model.addAttribute("unfollowButton",
					userRepository.findByEmail(userloggin).getFollowing().contains(userRepository.findByName(name)));
		}
		return "public-client";
	}

	@ResponseBody
	@JsonView(User.Basic.class)
	@RequestMapping(value = "/clients/", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public User addClient(@RequestBody User user, HttpSession session) {
		session.setMaxInactiveInterval(-1);
		return user;
	}

	@ResponseBody
	@RequestMapping(value = "/clients/", method = RequestMethod.GET)
	public ResponseEntity<Page<User>> getClients(HttpSession session, Pageable page) {
		session.setMaxInactiveInterval(-1);
		Page<User> users = userRepository.findAll(page);
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@ResponseBody
	@RequestMapping(value = "/clients/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> getClient(@PathVariable long id, HttpSession session) {
		session.setMaxInactiveInterval(-1);
		return new ResponseEntity<>(userRepository.findById(id), HttpStatus.OK);
	}

	@JsonView(User.Basic.class)
	@RequestMapping("/private-client/")
	public String privateClient(Model model, HttpServletRequest request, Authentication authentication,
			@RequestParam(required = false) String username, @RequestParam(required = false) String useremail,
			@RequestParam(required = false) String userdescription,
			@RequestParam(required = false) String favouritefood, @RequestParam(required = false) String password,
			@RequestParam(required = false) String confirmpassword, @RequestParam(required = false) Integer userage) {
		try {
			String userloggin = authentication.getName();
			String fileName = "profileImage" + userRepository.findByEmail(userloggin).getId() + ".jpg";
			model.addAttribute("fileName", fileName);
			if (request.isUserInRole("USER")) {
				model.addAttribute("user", userRepository.findByEmail(userloggin));
				model.addAttribute("restaurants", userRepository.findByEmail(userloggin).getRestaurants());
				model.addAttribute("following", userRepository.findByEmail(userloggin).getFollowing());
				model.addAttribute("bookings", userRepository.findByEmail(userloggin).getBookings());
				model.addAttribute("vouchers", userRepository.findByEmail(userloggin).getUserVouchers());
				model.addAttribute("reviews", userRepository.findByEmail(userloggin).getReviews());
				model.addAttribute("generalRestaurants", restaurantRepository.findAll());

				if (username != null) {
					User user = userRepository.findByEmail(userloggin);
					user.setName(username);
					user.setEmail(useremail);
					user.setDescription(userdescription);
					user.setFavouriteFood(favouritefood);
					if (password.equals(confirmpassword)) {
						user.setPassword(password);
					}
					userRepository.save(user);
					if ((userage > 10) && (userage < 100))
						user.setAge(userage);
				}
			}
		} catch (NullPointerException ex) {
			ex.printStackTrace();

		}
		return "private-client";
	}

}
