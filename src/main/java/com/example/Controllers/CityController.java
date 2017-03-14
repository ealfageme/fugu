package com.example.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Entities.City;
import com.example.Repositories.CityRepository;
import com.fasterxml.jackson.annotation.JsonView;

@Controller
public class CityController {
	
	@Autowired
	private CityRepository cityRepository;
	
	@JsonView(City.Basic.class)
	@RequestMapping("/city/{name}")
	public String city(Model model, @PathVariable String name) {
		model.addAttribute("city", cityRepository.findByName(name));
		model.addAttribute("restaurants", cityRepository.findByName(name).getCityResturants());

		return "city";
	}
}
