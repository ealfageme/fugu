package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String main(Model model, Restaurant restaurant) {
		model.addAttribute("restaurant",
				restaurantRepository.findAll(new Sort(new Order(Sort.Direction.DESC, "rate"))));
		return "main";
	}

	@RequestMapping("/city/{name}")
	public String city(Model model, @PathVariable String name) {
		model.addAttribute("city", cityRepository.findByName(name));
		model.addAttribute("restaurants", cityRepository.findByName(name).getCityResturants());

		return "city";
	}

	@RequestMapping("/private-client/{name}")
	public String privateClient(Model model, @PathVariable String name) {
		model.addAttribute("user", userRepository.findByName(name));
		model.addAttribute("restaurants", userRepository.findByName(name).getRestaurants());
		model.addAttribute("bookings", userRepository.findByName(name).getBookings());
		model.addAttribute("vouchers", userRepository.findByName(name).getUserVouchers());
		model.addAttribute("reviews", userRepository.findByName(name).getReviews());
		model.addAttribute("generalRestaurants", restaurantRepository.findAll());
		return "private-client";
	}

	@RequestMapping("/private-restaurant/{name}")
	public String privateRestaurant(Model model, @PathVariable String name) {
		model.addAttribute("restaurant", restaurantRepository.findByName(name));
		model.addAttribute("menu", restaurantRepository.findByName(name).getMenus());
		model.addAttribute("bookings", restaurantRepository.findByName(name).getBookings());
		model.addAttribute("vouchers", restaurantRepository.findByName(name).getVouchers());
		model.addAttribute("reviews", restaurantRepository.findByName(name).getRestaurantReviews());
		return "private-restaurant";
	}

	@RequestMapping("/public-restaurant/{name}")
	public String publicRestaurant(Model model, @PathVariable String name) {

		model.addAttribute("restaurant", restaurantRepository.findByName(name));
		model.addAttribute("menu", restaurantRepository.findByName(name).getMenus());
		model.addAttribute("vouchers", restaurantRepository.findByName(name).getVouchers());
		model.addAttribute("reviews", restaurantRepository.findByName(name).getRestaurantReviews());
		return "public-restaurant";
	}

	@RequestMapping("/public-client/{name}")
	public String publicClient(Model model, @PathVariable String name) {
		model.addAttribute("user", userRepository.findByName(name));
		model.addAttribute("restaurants", userRepository.findByName(name).getRestaurants());
		model.addAttribute("bookings", userRepository.findByName(name).getBookings());
		model.addAttribute("vouchers", userRepository.findByName(name).getUserVouchers());
		model.addAttribute("reviews", userRepository.findByName(name).getReviews());
		model.addAttribute("generalRestaurants", restaurantRepository.findAll());
		return "public-client";
	}

	@RequestMapping("/search-web/")
	public String searchWeb(Model model, @RequestParam(required=false) String city, @RequestParam(required=false) String foodType, @RequestParam(required=false) Double min, @RequestParam(required=false) Double max, @RequestParam(required=false) Double minPrice, @RequestParam(required=false) Double maxPrice) {
		if(city!=null&&foodType!=null){
			model.addAttribute("restaurants", restaurantRepository.findByFoodTypeAndCityName(foodType, city));
		}
		if(min!=null&&max!=null&&minPrice!=null&&maxPrice!=null){
			
			model.addAttribute("restaurants", restaurantRepository.findByMenuPriceBetweenAndRateBetween(minPrice, maxPrice, minPrice, maxPrice));
		}/*else{
			model.addAttribute("restaurants", restaurantRepository.findAll());
		}*/
		
		return "search-web";
	}

	
}