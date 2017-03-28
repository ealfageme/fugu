package com.example.Controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Entities.Restaurant;
import com.example.Entities.User;
import com.example.Repositories.CityRepository;
import com.example.Repositories.RestaurantRepository;
import com.example.Repositories.UserRepository;

@Controller
public class SearchWebController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RestaurantRepository restaurantRepository;
	@Autowired
	private CityRepository cityRepository;

	@RequestMapping(value = "/search-web/", method = { RequestMethod.GET, RequestMethod.POST })
	public String main(@RequestParam(required = false) String name, @RequestParam(required = false) String city,
			@RequestParam(required = false) String foodType, @RequestParam(required = false) Double min,
			@RequestParam(required = false) Double max, @RequestParam(required = false) Double minPrice,
			@RequestParam(required = false) Double maxPrice, Model model, HttpServletRequest request,
			Authentication authentication, @RequestParam(required = false) String restaurantname,
			@RequestParam(required = false) String restaurantaddress, @RequestParam(required = false) String kindoffood,
			@RequestParam(required = false) String restaurantcity,
			@RequestParam(required = false) String restaurantemail,
			@RequestParam(required = false) String restaurantphone,
			@RequestParam(required = false) String restaurantdescription,
			@RequestParam(required = false) String restaurantpassword, @RequestParam(required = false) String username,
			@RequestParam(required = false) String useremail, @RequestParam(required = false) String userage,
			@RequestParam(required = false) String favouritefood,
			@RequestParam(required = false) String userdescription,
			@RequestParam(required = false) String userpassword) {
		if (name != null) {
			model.addAttribute("restaurants", restaurantRepository.findByNameIgnoreCase(name));
		} else if (city != null && foodType != null) {
			model.addAttribute("restaurants", restaurantRepository.findByFoodTypeAndCityName(foodType, city));
		}
		if (name != null) {
			model.addAttribute("restaurants", restaurantRepository.findByNameIgnoreCase(name));
		} else if (min != null && max != null && minPrice != null && maxPrice != null) {
			model.addAttribute("restaurants",
					restaurantRepository.findByMenuPriceBetweenAndRateBetween(minPrice, maxPrice, min, max));
		}
		model.addAttribute("restaurant", restaurantRepository.findByRateBetweenOrderByRateDesc(new Double(0.0),
				new Double(5.0), new PageRequest(0, 4)));
		if (restaurantname != null) {
			Restaurant rest = new Restaurant(restaurantname, restaurantaddress, restaurantdescription, restaurantemail,
					kindoffood, Integer.parseInt(restaurantphone), 0, 0, restaurantpassword, true, true, true);
			rest.setCity(cityRepository.findByName(restaurantcity));
			restaurantRepository.save(rest);
		}
		if (username != null) {
			User user = new User(username, useremail, userdescription, userpassword, Integer.parseInt(userage),
					favouritefood);
			userRepository.save(user);
		}
		model.addAttribute("inSession", (request.isUserInRole("USER")||request.isUserInRole("RESTAURANT")));
		model.addAttribute("outSession", !request.isUserInRole("USER")&&!request.isUserInRole("RESTAURANT"));
		
		if(request.isUserInRole("USER")){
			System.out.println(authentication.getName());
			model.addAttribute("feedbackname", userRepository.findByEmail(authentication.getName()).getName());
			model.addAttribute("feedbackemail", authentication.getName());
		}else if(request.isUserInRole("RESTAURANT")){
			model.addAttribute("feedbackname", restaurantRepository.findByEmail(authentication.getName()).getName());
			model.addAttribute("feedbackemail", authentication.getName());
		}

		return "search-web";
	}
}
