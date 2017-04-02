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
import com.example.Services.ClientService;

@Controller
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@RequestMapping("/public-client/{name}")
	public String publicClient(Model model, HttpServletRequest request, Authentication authentication,
			@PathVariable String name, @RequestParam(required = false) String followPulsed,
			@RequestParam(required = false) String unfollowPulsed) {

		model.addAttribute("user", clientService.userRepositoryfindByName(name));
		model.addAttribute("restaurants", clientService.userRepositoryfindByName(name).getRestaurants());
		model.addAttribute("following", clientService.userRepositoryfindByName(name).getFollowing());
		model.addAttribute("bookings", clientService.userRepositoryfindByName(name).getBookings());
		model.addAttribute("vouchers", clientService.userRepositoryfindByName(name).getUserVouchers());
		model.addAttribute("reviews", clientService.userRepositoryfindByName(name).getReviews());
		model.addAttribute("generalRestaurants", clientService.restaurantRepositoryfindAll());
		model.addAttribute("inSession", (request.isUserInRole("USER") || request.isUserInRole("RESTAURANT")));
		String fileName = "profileImage" + clientService.userRepositoryfindByName(name).getId() + ".jpg";
		model.addAttribute("fileName", fileName);
		if (request.isUserInRole("USER")) {
			String userloggin = authentication.getName();
			model.addAttribute("followButton",
					!clientService.userRestaurantfindByEmail(userloggin).getFollowing().contains(clientService.userRepositoryfindByName(name)));
			model.addAttribute("unfollowButton",
					clientService.userRestaurantfindByEmail(userloggin).getFollowing().contains(clientService.userRepositoryfindByName(name)));

			if (followPulsed != null) {
				clientService.userRestaurantfindByEmail(userloggin).getFollowing().add(clientService.userRepositoryfindByName(name));
				clientService.userRepositorysave(clientService.userRestaurantfindByEmail(userloggin));
			}

			model.addAttribute("followButton",
					!clientService.userRestaurantfindByEmail(userloggin).getFollowing().contains(clientService.userRepositoryfindByName(name)));
			model.addAttribute("unfollowButton",
					clientService.userRestaurantfindByEmail(userloggin).getFollowing().contains(clientService.userRepositoryfindByName(name)));

			if (unfollowPulsed != null) {
				clientService.userRestaurantfindByEmail(userloggin).getFollowing().remove(clientService.userRepositoryfindByName(name));
				clientService.userRepositorysave(clientService.userRestaurantfindByEmail(userloggin));
			}

			model.addAttribute("followButton",
					!clientService.userRestaurantfindByEmail(userloggin).getFollowing().contains(clientService.userRepositoryfindByName(name)));
			model.addAttribute("unfollowButton",
					clientService.userRestaurantfindByEmail(userloggin).getFollowing().contains(clientService.userRepositoryfindByName(name)));
		}
		return "public-client";
	}


	@RequestMapping("/private-client/")
	public String privateClient(Model model, HttpServletRequest request, Authentication authentication) {
		try {
			String userloggin = authentication.getName();
			String fileName;
			if(request.isUserInRole("FACEBOOK")){
				fileName = "profileImage1.jpg";
			}else{
				fileName = "profileImage" + clientService.userRestaurantfindByEmail(userloggin).getId() + ".jpg";
			}
			model.addAttribute("fileName", fileName);
			model.addAttribute("user", clientService.userRestaurantfindByEmail(userloggin));
			model.addAttribute("restaurants", clientService.userRestaurantfindByEmail(userloggin).getRestaurants());
			model.addAttribute("following", clientService.userRestaurantfindByEmail(userloggin).getFollowing());
			model.addAttribute("bookings", clientService.userRestaurantfindByEmail(userloggin).getBookings());
			model.addAttribute("vouchers", clientService.userRestaurantfindByEmail(userloggin).getUserVouchers());
			model.addAttribute("reviews", clientService.userRestaurantfindByEmail(userloggin).getReviews());
			model.addAttribute("generalRestaurants", clientService.restaurantRepositoryfindAll());
			model.addAttribute("bookingsAccepted", clientService.bookingRepositoryfindByStateAndBookingUser("Accepted",clientService.userRestaurantfindByEmail(userloggin)));
			model.addAttribute("bookingsInProcess", clientService.bookingRepositoryfindByStateAndBookingUser("In Process",clientService.userRestaurantfindByEmail(userloggin)));

			model.addAttribute("inNormalSession", request.isUserInRole("USER"));
			model.addAttribute("inFacebookSession", request.isUserInRole("FACEBOOK"));
			List<Restaurant> recommendedRestaurants = new ArrayList<>();
			User conectedUser = clientService.userRestaurantfindByEmail(userloggin);
			for(Restaurant rest : clientService.restaurantRepositoryfindAll()){
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
				User user = clientService.userRestaurantfindByEmail(authentication.getName());
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
