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
import com.example.Services.ClientService;

@Controller
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@RequestMapping("/public-client/{name}")
	public String publicClient(Model model, HttpServletRequest request, Authentication authentication,
			@PathVariable String name, @RequestParam (required = false) String followPulsed,
			@RequestParam (required = false) String unfollowPulsed) {
		User user = clientService.userRepositoryFindByName(name);
		model.addAttribute("user", user)
			.addAttribute("restaurants", user.getRestaurants())
			.addAttribute("following", user.getFollowing())
			.addAttribute("bookings", user.getBookings())
			.addAttribute("vouchers", user.getUserVouchers())
			.addAttribute("reviews", user.getReviews())
			.addAttribute("generalRestaurants", clientService.restaurantRepositoryFindAll())
			.addAttribute("inSession", (request.isUserInRole("USER") || request.isUserInRole("RESTAURANT")));
		String fileName = "profileImage" + clientService.userRepositoryFindByName(name).getId() + ".jpg";
		model.addAttribute("fileName", fileName);
		if (request.isUserInRole("USER")) {
			String userloggin = authentication.getName();
			User userlogged = clientService.userRepositoryFindByEmail(userloggin);
			model.addAttribute("followButton",
					!userlogged.getFollowing().contains(user));
			model.addAttribute("unfollowButton",
					userlogged.getFollowing().contains(user));

			if (followPulsed != null) {
				userlogged.getFollowing().add(user);
				clientService.userRepositorysave(userlogged);
			}

			model.addAttribute("followButton",
					!userlogged.getFollowing().contains(user));
			model.addAttribute("unfollowButton",
					userlogged.getFollowing().contains(user));

			if (unfollowPulsed != null) {
				userlogged.getFollowing().remove(user);
				clientService.userRepositorysave(userlogged);
			}

			model.addAttribute("followButton",
					!userlogged.getFollowing().contains(user));
			model.addAttribute("unfollowButton",
					userlogged.getFollowing().contains(user));
		}
		return "public-client";
	}


	@RequestMapping("/private-client/")
	public String privateClient(Model model, HttpServletRequest request, Authentication authentication) {
		try {
			String userloggin = authentication.getName();
			User userlogged = clientService.userRepositoryFindByEmail(userloggin);
			String fileName;
			if (request.isUserInRole("FACEBOOK")) {
				fileName = "profileImage1.jpg";
			} else {
				fileName = "profileImage" + userlogged.getId() + ".jpg";
			}
			model.addAttribute("fileName", fileName)
				.addAttribute("user", userlogged)
				.addAttribute("restaurants", userlogged.getRestaurants())
				.addAttribute("following", userlogged.getFollowing())
				.addAttribute("bookings", userlogged.getBookings())
				.addAttribute("vouchers", userlogged.getUserVouchers())
				.addAttribute("reviews", userlogged.getReviews())
				.addAttribute("generalRestaurants", clientService.restaurantRepositoryFindAll())
				.addAttribute("bookingsAccepted", clientService.bookingRepositoryFindByStateAndBookingUser("Accepted",userlogged))
				.addAttribute("bookingsInProcess", clientService.bookingRepositoryFindByStateAndBookingUser("In Process",userlogged))
				.addAttribute("inNormalSession", request.isUserInRole("USER"))
				.addAttribute("inFacebookSession", request.isUserInRole("FACEBOOK"));
			List<Restaurant> recommendedRestaurants = new ArrayList<>();
			for (Restaurant rest : clientService.restaurantRepositoryFindAll()) {
				if ((rest.getFoodType().equalsIgnoreCase(userlogged.getFavouriteFood())) && !(userlogged.getRestaurants().contains(rest))) {
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
				User user = clientService.userRepositoryFindByEmail(authentication.getName());
				user.setName(username);
				user.setEmail(useremail);
				user.setDescription(userdescription);
				user.setFavouriteFood(favouritefood);
				if (password.equals(confirmpassword)) {
					user.setPassword(password);
				}
				clientService.userRepositorysave(user);
				if ((userage > 10) && (userage < 100))
					user.setAge(userage);
			}
		}
		return "redirect:/private-client/";
	}

}
