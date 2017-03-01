package com.example;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DataBaseUsage implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RestaurantRepository restaurantRepository;
	/*private ReviewRepository reviewRepository;
	private VoucherRepository voucherRepository;
	private BookingRepository bookingRepository;*/
	
	@Override
	public void run (String... args) throws Exception{
		Restaurant rest1 = new Restaurant ("Mesón Paco","Madrid","desc","email","italian",123213123,"sadd");
		Restaurant rest2 = new Restaurant ("Mesón mariano","Barcelona","desc1","email1","italian1",123213123,"s1add");
		Restaurant rest3 = new Restaurant ("Mesón Felipe","Valencia","desc","email","italian",123213123,"sadd");
		Restaurant rest4 = new Restaurant ("Mesón Lito","Sevilla","desc1","email1","italian1",123213123,"s1add");
		Restaurant rest5 = new Restaurant ("Mesón Daniel","Valladolid","desc","email","italian",123213123,"sadd");
		Restaurant rest6 = new Restaurant ("Mesón Eulalio","Sevilla","desc1","email1","italian1",123213123,"s1add");
		Restaurant rest7 = new Restaurant ("Mesón Eusebio","Málaga","desc","email","italian",123213123,"sadd");
		Restaurant rest8 = new Restaurant ("Mesón Gento","Burgos","desc1","email1","italian1",123213123,"s1add");
		Restaurant rest9 = new Restaurant ("Mesón Genaro","Cáceres","desc","email","italian",123213123,"sadd");
		Restaurant rest10 = new Restaurant ("Mesón Agapito","Santiago","desc1","email1","italian1",123213123,"s1add");
		
		restaurantRepository.save(rest1);
		restaurantRepository.save(rest2);
		restaurantRepository.save(rest3);
		restaurantRepository.save(rest4);
		restaurantRepository.save(rest5);
		restaurantRepository.save(rest6);
		restaurantRepository.save(rest7);
		restaurantRepository.save(rest8);
		restaurantRepository.save(rest9);
		restaurantRepository.save(rest10);
		User user1 = new User("john-snow","john-snow@website.com","GoT actor","yomuero",21);
		User user2 = new User("john-cena","john-cena@website.com","WWE actor","tututuuu",31);
		User user3 = new User("john-travolta","john-travolta@website.com","singler actor","sdfmd",54);
		
		/*rest1.getUsers().add(user1);
		rest1.getUsers().add(user2);
		rest2.getUsers().add(user3);*/
		user1.getRestaurant().add(rest1);
		user1.getRestaurant().add(rest2);
		user2.getRestaurant().add(rest1);
		user3.getRestaurant().add(rest2);
		
		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.save(user3);
		
		
	
	}
	@PostConstruct
	public void init (){
		
		
	}
	
	interface RestaurantListView extends Restaurant.BasicAtt, Restaurant.UserAtt, User.BasicAtt{}
	
	@RequestMapping("main")
	public String main(Model model) {
		model.addAttribute("restaurant-name1",restaurantRepository.findAll().get(0).getName());
		model.addAttribute("restaurant-name2",restaurantRepository.findAll().get(1).getName());
		model.addAttribute("restaurant-name3",restaurantRepository.findAll().get(2).getName());
		model.addAttribute("restaurant-name4",restaurantRepository.findAll().get(3).getName());
		model.addAttribute("restaurant-name5",restaurantRepository.findAll().get(4).getName());
		model.addAttribute("restaurant-name6",restaurantRepository.findAll().get(5).getName());
		model.addAttribute("restaurant-name7",restaurantRepository.findAll().get(6).getName());
		model.addAttribute("restaurant-name8",restaurantRepository.findAll().get(7).getName());
		model.addAttribute("restaurant-name9",restaurantRepository.findAll().get(8).getName());
		model.addAttribute("restaurant-name10",restaurantRepository.findAll().get(9).getName());
		model.addAttribute("restaurant-address1",restaurantRepository.findAll().get(0).getAddress());
		model.addAttribute("restaurant-address2",restaurantRepository.findAll().get(1).getAddress());
		model.addAttribute("restaurant-address3",restaurantRepository.findAll().get(2).getAddress());
		model.addAttribute("restaurant-address4",restaurantRepository.findAll().get(3).getAddress());
		model.addAttribute("restaurant-address5",restaurantRepository.findAll().get(4).getAddress());
		model.addAttribute("restaurant-address6",restaurantRepository.findAll().get(5).getAddress());
		model.addAttribute("restaurant-address7",restaurantRepository.findAll().get(6).getAddress());
		model.addAttribute("restaurant-address8",restaurantRepository.findAll().get(7).getAddress());
		model.addAttribute("restaurant-address9",restaurantRepository.findAll().get(8).getAddress());
		model.addAttribute("restaurant-address10",restaurantRepository.findAll().get(9).getAddress());
		
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
