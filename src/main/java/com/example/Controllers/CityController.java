package com.example.Controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.Repositories.CityRepository;
import com.example.Services.CityService;

@Controller
public class CityController {
	
	@Autowired
	private CityService cityService;
	
	@RequestMapping("/city/{name}")
	public String city(Model model, @PathVariable String name, HttpServletRequest request) {
		model.addAttribute("city",cityService.cityServiceFindByName(name));
		model.addAttribute("restaurants", cityService.cityServiceFindByName(name).getCityResturants());
		model.addAttribute("inSession", (request.isUserInRole("USER")||request.isUserInRole("RESTAURANT")));

		return "city";
	}
}
