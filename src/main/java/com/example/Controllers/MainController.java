package com.example.Controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.annotation.JsonView;
import com.example.Entities.*;
import com.example.Repositories.*;
import com.example.Security.*;
import com.example.Controllers.*;

@Controller
public class MainController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RestaurantRepository restaurantRepository;
	@Autowired
	private CityRepository cityRepository;

	@RequestMapping("/main/")
	public String main(Model model, Restaurant restaurant, @RequestParam(required=false) String restaurantname, 
			 @RequestParam(required=false) String restaurantaddress,@RequestParam(required=false) String kindoffood,
			 @RequestParam(required=false) String restaurantcity,	@RequestParam(required=false) String restaurantemail,
			 @RequestParam(required=false) String restaurantphone,			@RequestParam(required=false) String restaurantdescription,
			 @RequestParam(required=false) String restaurantpassword,	@RequestParam(required=false) String username,
				@RequestParam(required=false) String useremail,	@RequestParam(required=false) String userage,
				@RequestParam(required=false) String favouritefood,	@RequestParam(required=false) String userdescription,
				@RequestParam(required=false) String userpassword) {	
		model.addAttribute("restaurant", restaurantRepository.findByRateBetweenOrderByRateDesc(new Double(0.0), new Double(5.0), new PageRequest(0, 4)));
		System.out.println(restaurantname);
		if (restaurantname!=null){
			Restaurant rest= new Restaurant (restaurantname,restaurantaddress,restaurantdescription,restaurantemail,kindoffood,Integer.parseInt(restaurantphone), 0, 0,restaurantpassword,true,true,true,"ROLE_RESTAURANT");
			rest.setCity(cityRepository.findByName(restaurantcity));
			restaurantRepository.save(rest);
		}
		if (username!=null){
			User user = new User(username,useremail,userdescription, userpassword ,Integer.parseInt(userage),favouritefood,"ROLE_USER");
			userRepository.save(user);
		}
		return "main";
	}

	
	/*@RequestMapping("/error/")
	public String error(Model model) {
		return "error";
	}*/

	
}