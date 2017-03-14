package com.example.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Entities.Restaurant;
import com.example.Entities.User;
import com.example.Repositories.RestaurantRepository;
import com.example.Repositories.UserRepository;

@Controller
public class SearchWebController {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@RequestMapping("/search-web/")
	public String searchWeb(Model model, @RequestParam(required=false) String name, @RequestParam(required=false) String city,
			@RequestParam(required=false) String foodType, @RequestParam(required=false) Double min,
			@RequestParam(required=false) Double max, @RequestParam(required=false) Double minPrice,
			@RequestParam(required=false) Double maxPrice,@RequestParam(required=false) String restaurantname, 
			 @RequestParam(required=false) String address,@RequestParam(required=false) String kindoffood,
			 @RequestParam(required=false) String email,@RequestParam(required=false) String password,
			 @RequestParam(required=false) String username,@RequestParam(required=false) String useremail,
			 @RequestParam(required=false) String userpassword,@RequestParam(required=false) String favouritefood ) {
		if(name!=null){
			model.addAttribute("restaurants", restaurantRepository.findByNameIgnoreCase(name));
		}
		else if(city!=null&&foodType!=null){
			model.addAttribute("restaurants", restaurantRepository.findByFoodTypeAndCityName(foodType, city));
		}
		if(name!=null){
			model.addAttribute("restaurants", restaurantRepository.findByNameIgnoreCase(name));
		}
		else if(min!=null&&max!=null&&minPrice!=null&&maxPrice!=null){	
			model.addAttribute("restaurants", restaurantRepository.findByMenuPriceBetweenAndRateBetween(minPrice, maxPrice, min, max));
		}
		if (restaurantname!=null){
			Restaurant rest= new Restaurant (restaurantname,address,"",email,kindoffood,0, 0, 0,password,true,true,true,"ROLE_RESTAURANT"+restaurantname);
			restaurantRepository.save(rest);}
			if (username!=null){
			User user = new User(username,useremail,"", userpassword ,18,favouritefood,"ROLE_USER"+username);
			userRepository.save(user);}
		
		return "search-web";
	}
}
