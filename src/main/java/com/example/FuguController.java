package com.example;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FuguController {
	

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