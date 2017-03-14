package com.example.Controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String publicClient(Model model, @PathVariable String name,  @RequestParam(required=false) String userName) {
		model.addAttribute("user", userRepository.findByName(name));
		model.addAttribute("restaurants", userRepository.findByName(name).getRestaurants());
		model.addAttribute("following", userRepository.findByName(name).getFollowing());
		model.addAttribute("bookings", userRepository.findByName(name).getBookings());
		model.addAttribute("vouchers", userRepository.findByName(name).getUserVouchers());
		model.addAttribute("reviews", userRepository.findByName(name).getReviews());
		model.addAttribute("generalRestaurants", restaurantRepository.findAll());
		if (userName!=null) {			
			userRepository.findByName("john-lennon").getFollowing().add(userRepository.findByName(name));
			userRepository.save(userRepository.findByName("john-lennon"));}
		return "public-client";
	}
	
	@JsonView(User.Basic.class)
	@RequestMapping("/private-client/")
	public String privateClient(Model model,HttpServletRequest request,Authentication authentication,
			@RequestParam(required=false) String username,@RequestParam(required=false) String useremail,@RequestParam(required=false) String userdescription,
			@RequestParam(required=false) String favouritefood, @RequestParam(required=false) String password,
			@RequestParam(required=false) String confirmpassword, @RequestParam(required= false) Integer userage) {
		try{
			if(request.isUserInRole("USER")){
				String userloggin = authentication.getName();
				model.addAttribute("user", userRepository.findByEmail(userloggin));
				model.addAttribute("restaurants", userRepository.findByEmail(userloggin).getRestaurants());
				model.addAttribute("following", userRepository.findByEmail(userloggin).getFollowing());
				model.addAttribute("bookings", userRepository.findByEmail(userloggin).getBookings());
				model.addAttribute("vouchers", userRepository.findByEmail(userloggin).getUserVouchers());
				model.addAttribute("reviews", userRepository.findByEmail(userloggin).getReviews());
				model.addAttribute("generalRestaurants", restaurantRepository.findAll());
				
				if (username!=null) {
					User user = userRepository.findByEmail(userloggin);
					user.setName(username);
					user.setEmail(useremail);
					user.setDescription(userdescription);
					user.setFavouriteFood(favouritefood);
					if (password.equals(confirmpassword)){
						user.setPassword(password);
					}
					userRepository.save(user);
					if ((userage>10) && (userage<100))
						user.setAge(userage);
				}
		}
		}catch(NullPointerException ex){
			ex.printStackTrace();
			
		}
		return "private-client";
	}
	
	
}
