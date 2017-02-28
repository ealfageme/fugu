package com.example;

import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DataBaseUsage implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	/*private RestaurantRepository restaurantRepository;
	private ReviewRepository reviewRepository;
	private VoucherRepository voucherRepository;
	private BookingRepository bookingRepository;*/
	
	@Override
	public void run (String... args) throws Exception{
		
		userRepository.save(new User("john-snow","john-snow@website.com","GoT actor","yomuero",21));
		userRepository.save(new User("john-cena","john-cena@website.com","WWE actor","tututuuu",31));
		userRepository.save(new User("john-travolta","john-travolta@website.com","singler actor","sdfmd",54));
	}

	@RequestMapping("main")
	public String main(Model model) {

		return "main";
	}
	@RequestMapping("city")
	public String city(Model model) {

		return "city";
	}
	@RequestMapping("private-client")
	public String privateCity(Model model) {
		ArrayList<User> users=new ArrayList<User>();
		users = (ArrayList<User>) userRepository.findAll();
		model.addAttribute("username",users.get(0).getName()) ;
		model.addAttribute("useremail",users.get(0).getEmail());
		model.addAttribute("userage",users.get(0).getAge());
		model.addAttribute("userdescription",users.get(0).getDescription());
		return "private-client";
	}
	@RequestMapping("private-restaurant")
	public String privateRestaurtan(Model model) {

		return "private-restaurant";
	}
	@RequestMapping("public-restaurant")
	public String publicRestaurant(Model model) {

		return "public-restaurant";
	}
	@RequestMapping("public-client")
	public String publicClient(Model model) {

		return "public-client";
	}
	@RequestMapping("search-web")
	public String searchWeb(Model model) {

		return "search-web";
	}

	
	
}
