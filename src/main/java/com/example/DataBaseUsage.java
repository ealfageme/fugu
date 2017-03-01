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
	@Autowired
	private MenuRepository menuRepository;
	/*private ReviewRepository reviewRepository;
	private VoucherRepository voucherRepository;
	private BookingRepository bookingRepository;*/
	
	@Override
	public void run (String... args) throws Exception{
		Menu menu1 = new Menu("espaguetis", 10.5);
		Menu menu2 = new Menu("EloyMolaMil", 200.5);
		Menu menu3 = new Menu("Cosa verde (lechugo)", 0.0);
		menuRepository.save(menu1);
		menuRepository.save(menu2);
		menuRepository.save(menu3);
		Restaurant rest1 = new Restaurant ("nombre","dire","desc","email","italian",123213123,"sadd");
		Restaurant rest2 = new Restaurant ("nombre1","dire1","desc1","email1","italian1",123213123,"s1add");
		Restaurant rest3 = new Restaurant ("nombre3","dire3","desc3","email3","italian3",123233,"sallmushalim");
		rest1.getMenus().add(menu1);
		rest2.getMenus().add(menu2);
		rest3.getMenus().add(menu3);
		restaurantRepository.save(rest1);
		restaurantRepository.save(rest2);
		restaurantRepository.save(rest3);
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
