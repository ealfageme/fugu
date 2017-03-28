package com.example.Controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.Entities.Restaurant;
import com.example.Entities.User;
import com.example.Repositories.BookingRepository;
import com.example.Repositories.RestaurantRepository;
import com.example.Repositories.UserRepository;

@Controller
public class ClientController {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BookingRepository bookingRepository;
	@Autowired
	private RestaurantRepository restaurantRepository;
	
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
		if (request.isUserInRole("USER")) {
			String userloggin = authentication.getName();
			model.addAttribute("followButton",
					!userRepository.findByEmail(userloggin).getFollowing().contains(userRepository.findByName(name)));
			model.addAttribute("unfollowButton",
					userRepository.findByEmail(userloggin).getFollowing().contains(userRepository.findByName(name)));

			if (followPulsed != null) {
				userRepository.findByEmail(userloggin).getFollowing().add(userRepository.findByName(name));
				userRepository.save(userRepository.findByEmail(userloggin));
			}

			model.addAttribute("followButton",
					!userRepository.findByEmail(userloggin).getFollowing().contains(userRepository.findByName(name)));
			model.addAttribute("unfollowButton",
					userRepository.findByEmail(userloggin).getFollowing().contains(userRepository.findByName(name)));

			if (unfollowPulsed != null) {
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


	@RequestMapping("/private-client/")
	public String privateClient(Model model, HttpServletRequest request, Authentication authentication) {
		try {
			String userloggin = authentication.getName();
			String fileName = "profileImage" + userRepository.findByEmail(userloggin).getId() + ".jpg";
			model.addAttribute("fileName", fileName);
			model.addAttribute("user", userRepository.findByEmail(userloggin));
			model.addAttribute("restaurants", userRepository.findByEmail(userloggin).getRestaurants());
			model.addAttribute("following", userRepository.findByEmail(userloggin).getFollowing());
			model.addAttribute("bookings", userRepository.findByEmail(userloggin).getBookings());
			model.addAttribute("vouchers", userRepository.findByEmail(userloggin).getUserVouchers());
			model.addAttribute("reviews", userRepository.findByEmail(userloggin).getReviews());
			model.addAttribute("generalRestaurants", restaurantRepository.findAll());
			model.addAttribute("bookingsAccepted", bookingRepository.findByStateAndBookingUser("Accepted",userRepository.findByEmail(userloggin)));
			model.addAttribute("bookingsInProcess", bookingRepository.findByStateAndBookingUser("In Process",userRepository.findByEmail(userloggin)));

			List<Restaurant> recommendedRestaurants = new ArrayList<>();
			User conectedUser = userRepository.findByEmail(userloggin);
			for(Restaurant rest : restaurantRepository.findAll()){
				if ((rest.getFoodType().equalsIgnoreCase(conectedUser.getFavouriteFood()))&&!(conectedUser.getRestaurants().contains(rest))){
					recommendedRestaurants.add(rest);
				}	
			model.addAttribute("recommendedRestaurants", recommendedRestaurants);
			}
		} catch (NullPointerException ex) {
			ex.printStackTrace();

		}
		return "private-client";
	}
	
	@RequestMapping("/private-client/modify")
	public String modifyClient(Model model, HttpServletRequest request, Authentication authentication,
			@RequestParam(required = false) String username, @RequestParam(required = false) String useremail,
			@RequestParam(required = false) String userdescription,
			@RequestParam(required = false) String favouritefood, @RequestParam(required = false) String password,
			@RequestParam(required = false) String confirmpassword, @RequestParam(required = false) Integer userage){
		if (request.isUserInRole("USER")) {
			if (username != null) {
				User user = userRepository.findByEmail(authentication.getName());
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
		return "redirect:/private-client/";
	}

}
