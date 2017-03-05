package com.example;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FuguController {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RestaurantRepository restaurantRepository;
	@Autowired
	private MenuRepository menuRepository;
	@Autowired
	private VoucherRepository voucherRepository;
	@Autowired
	private ReviewRepository reviewRepository;
	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private BookingRepository bookingRepository;

	@RequestMapping("/main/")
	public String main(Model model) {	
		model.addAttribute("restaurant",restaurantRepository.findAll());
		return "main";
	}
	@RequestMapping("/city/{id}")
	public String city(Model model, @PathVariable long id) {
		model.addAttribute("city", cityRepository.findOne(id));
		model.addAttribute("restaurants", cityRepository.findOne(id).getCityResturants());
		
		return "city";
	}
	@RequestMapping("/private-client/{id}")
	public String privateClient(Model model, @PathVariable long id) {
		model.addAttribute("user", userRepository.findOne(id));
		model.addAttribute("restaurants", userRepository.findOne(id).getRestaurants());
		model.addAttribute("bookings", userRepository.findOne(id).getBookings());
		model.addAttribute("vouchers", userRepository.findOne(id).getUserVouchers());
		model.addAttribute("reviews", userRepository.findOne(id).getReviews());
		model.addAttribute("generalRestaurants", restaurantRepository.findAll());
		return "private-client";
	}
	@RequestMapping("/private-restaurant/{id}")
	public String privateRestaurant(Model model,@PathVariable long id) {
		model.addAttribute("restaurant",restaurantRepository.findOne(id));
		model.addAttribute("menu",restaurantRepository.findOne(id).getMenus());
		model.addAttribute("bookings",restaurantRepository.findOne(id).getBookings());
		model.addAttribute("vouchers",restaurantRepository.findOne(id).getVouchers());
		model.addAttribute("reviews",restaurantRepository.findOne(id).getRestaurantReviews());
		return "private-restaurant";
	}
		
	@RequestMapping("/public-restaurant/{id}")
	public String publicRestaurant(Model model, @PathVariable long id) {
		
		model.addAttribute("restaurant",restaurantRepository.findOne(id));
		model.addAttribute("menu",restaurantRepository.findOne(id).getMenus());
		model.addAttribute("vouchers",restaurantRepository.findOne(id).getVouchers());
		model.addAttribute("reviews",restaurantRepository.findOne(id).getRestaurantReviews());
		return "public-restaurant";
	}
	@RequestMapping("/public-client/{id}")
	public String publicClient(Model model, @PathVariable long id) {
		model.addAttribute("user", userRepository.findOne(id));
		model.addAttribute("restaurants", userRepository.findOne(id).getRestaurants());
		model.addAttribute("bookings", userRepository.findOne(id).getBookings());
		model.addAttribute("vouchers", userRepository.findOne(id).getUserVouchers());
		model.addAttribute("reviews", userRepository.findOne(id).getReviews());
		model.addAttribute("generalRestaurants", restaurantRepository.findAll());
		return "public-client";
	}
	@RequestMapping("/search-web/")
	public String searchWeb(Model model) {
		model.addAttribute("restaurants", restaurantRepository.findAll());
		return "search-web";
	}

	
}