package com.example.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

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
		/*
		 * Following feature has to be implemented
		 */
		if (userName!=null) {			
			userRepository.findByName("john-lennon").getFollowing().add(userRepository.findByName(name));
			userRepository.save(userRepository.findByName("john-lennon"));}
		return "public-client";
	}
	
	@ResponseBody
	@JsonView(User.Basic.class)
	@RequestMapping(value="/clients/", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public User addClient(@RequestBody User user) {
		return user;
	}
	
	@ResponseBody
	@JsonView(User.Basic.class)
	@RequestMapping(value="/clients/", method=RequestMethod.GET)
	public ResponseEntity<List<User>> getClients() {
		return  new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
	}
	
	@ResponseBody
	@JsonView(User.Basic.class)
	@RequestMapping(value="/clients/{id}", method=RequestMethod.GET)
	public ResponseEntity<User> getClient(@PathVariable long id) {
		return  new ResponseEntity<>(userRepository.findById(id), HttpStatus.OK);
	}
	
	@JsonView(User.Basic.class)
	@RequestMapping("/private-client/{name}")
	public String privateClient(Model model, @PathVariable String name,@RequestParam(required=false) String username,
			@RequestParam(required=false) String useremail,@RequestParam(required=false) String userdescription,
			@RequestParam(required=false) String favouritefood, @RequestParam(required=false) String password,
			@RequestParam(required=false) String confirmpassword, @RequestParam(required= false) Integer userage) {

		model.addAttribute("user", userRepository.findByName(name));
		model.addAttribute("restaurants", userRepository.findByName(name).getRestaurants());
		model.addAttribute("following", userRepository.findByName(name).getFollowing());
		model.addAttribute("bookings", userRepository.findByName(name).getBookings());
		model.addAttribute("vouchers", userRepository.findByName(name).getUserVouchers());
		model.addAttribute("reviews", userRepository.findByName(name).getReviews());
		model.addAttribute("generalRestaurants", restaurantRepository.findAll());
		
		if (username!=null) {
			User user = userRepository.findByName(name);
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

			return "redirect:/private-client/"+username+".html";

		}
		return "private-client";
	}
	
	
}
