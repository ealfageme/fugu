package com.example;

import java.util.ArrayList;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class DataBaseUsage implements CommandLineRunner{
	
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
	
	@Override
	public void run (String... args) throws Exception{
		
		Menu menu1 = new Menu("Nachos", 10.5," with cheese");
		Menu menu2 = new Menu("BBQ Wings Chicken Bites", 20.5, "with sauce");
		Menu menu3 = new Menu("Fries", 5.0,"with ketchup" );
		Menu menu4 = new Menu("Mediterranean Chicken", 12.5," with BBQ");
		Menu menu5 = new Menu("Chicken Pasta",3.5, "with sauce");
		Menu menu6 = new Menu("Vegetarian Pasta", 8.0,"with Salad" );
		Menu menu7 = new Menu("Beef Fajita", 6.5,"with ketchup" );
		Menu menu8 = new Menu("Beef Triple Burger", 12.5," with BBQ");
		Menu menu9 = new Menu("Georgian Wings",3.5, "with sauce");
		Menu menu10 = new Menu("Chicken Breast Sandwich", 8.0,"with Salad" );
		menuRepository.save(menu1);
		menuRepository.save(menu2);
		menuRepository.save(menu3);
		menuRepository.save(menu4);
		menuRepository.save(menu5);
		menuRepository.save(menu6);
		menuRepository.save(menu7);
		menuRepository.save(menu8);
		menuRepository.save(menu9);
		menuRepository.save(menu10);
		
		City city1 = new City("Madrid");
		City city2 = new City("Barcelona");
		City city3 = new City("Valencia");
		City city4 = new City("Cáceres");
		City city5 = new City("Málaga");
		cityRepository.save(city1);
		cityRepository.save(city2);
		cityRepository.save(city3);
		cityRepository.save(city4);
		cityRepository.save(city5);
		
		Restaurant rest1 = new Restaurant ("American Whey","Avenida España 43 ","tipical american food","american@whey.com","italian",658742154,"password");
		Restaurant rest2 = new Restaurant ("Mesón mariano","Avenida Colombia 4","desc1","email1","italian1",123213123,"s1add");
		Restaurant rest3 = new Restaurant ("Mesón Felipe","Avenida Barcelona 43","desc","email","italian",123213123,"sadd");
		Restaurant rest4 = new Restaurant ("Mesón Lito","Calle de la amagura 5","desc1","email1","italian1",123213123,"s1add");
		Restaurant rest5 = new Restaurant ("Mesón Daniel","Plaza de España 69","desc","email","italian",123213123,"sadd");
		Restaurant rest6 = new Restaurant ("Mesón Eulalio","Calle Margaret 12","desc1","email1","italian1",123213123,"s1add");
		Restaurant rest7 = new Restaurant ("Mesón Eusebio","Callejón Paco 1","desc","email","italian",123213123,"sadd");
		Restaurant rest8 = new Restaurant ("Mesón Gento","Calle azulona 76","desc1","email1","italian1",123213123,"s1add");
		Restaurant rest9 = new Restaurant ("Mesón Genaro","Plaza de la reina 3","desc","email","italian",123213123,"sadd");
		Restaurant rest10 = new Restaurant ("Mesón Agapito","Avenida Pablo","desc1","email1","italian1",123213123,"s1add");
		rest1.getMenus().add(menu1);
		rest2.getMenus().add(menu2);
		rest3.getMenus().add(menu3);
		rest1.setCity(city1);
		rest2.setCity(city2);
		rest3.setCity(city3);
		rest4.setCity(city3);
		rest5.setCity(city5);
		rest6.setCity(city4);
		rest7.setCity(city4);
		rest8.setCity(city1);
		rest9.setCity(city3);
		rest10.setCity(city1);

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
		user1.getRestaurant().add(rest1);
		user1.getRestaurant().add(rest2);
		user2.getRestaurant().add(rest1);
		user3.getRestaurant().add(rest2);
		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.save(user3);
		
		Voucher voucher1 = new Voucher("2*1 in salads","Come with a friend and eat for the half price",new Date(),10.5);
		Voucher voucher2 = new Voucher("FREE drinks on fridays","Every friday we offer free drinks with hamburger menus",new Date(),10.5);
		voucher1.getVoucherUsers().add(user1);
		voucher2.getVoucherUsers().add(user1);
		voucher1.setRestaurant(rest1);
		voucher2.setRestaurant(rest1);
		voucherRepository.save(voucher1);
		voucherRepository.save(voucher2);
		
		//RELACION USER - REVIEW
		/*
		Review review1 = new Review("Fucking amazing", 5.0, new Date());
		Review review2 = new Review("This restaurant must be improved", 2.2, new Date());
		Review review3 = new Review("If you want to spend a good time eating, come here", 4.1, new Date());
		review1.setUser(user1);
		review2.setUser(user2);
		review3.setUser(user3);
		reviewRepository.save(review1);
		reviewRepository.save(review2);
		reviewRepository.save(review3);
		*/
		
		//RELACION USER - BOOKING
		/*Booking booking1 = new Booking(new Date(), 5);
		Booking booking2 = new Booking(new Date(), 2);
		
		booking1.setUser(user1);
		booking2.setUser(user3);
		
		bookingRepository.save(booking1);
		bookingRepository.save(booking2);*/
		
		//RELACION RESTAURANTE - REVIEW
		/*Review review1 = new Review("Fucking amazing", 5.0, new Date());
		Review review2 = new Review("This restaurant must be improved", 2.2, new Date());
		Review review3 = new Review("If you want to spend a good time eating, come here", 4.1, new Date());
		
		review1.setReviewRestaurant(rest1);
		review2.setReviewRestaurant(rest2);
		review3.setReviewRestaurant(rest3);
		
		reviewRepository.save(review1);
		reviewRepository.save(review2);
		reviewRepository.save(review3);*/
	
	}
	@PostConstruct
	public void init (){
		
		
	}
	
	
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
	@RequestMapping("/private-restaurant/{id}")
	public String privateRestaurtan(Model model,@PathVariable long id) {
		model.addAttribute("restaurant",restaurantRepository.findOne(id));
		model.addAttribute("menu",restaurantRepository.findOne(id).getMenus());
		
		return "private-restaurant";
	}
	@RequestMapping("public-restaurant")
	public String publicRestaurant(Model model) {
		model.addAttribute("restaurant",restaurantRepository.findAll().get(0));
		model.addAttribute("menu1",menuRepository.findAll().get(0));
		model.addAttribute("menu2",menuRepository.findAll().get(1));
		model.addAttribute("menu3",menuRepository.findAll().get(2));
		model.addAttribute("menu4",menuRepository.findAll().get(3));
		model.addAttribute("menu5",menuRepository.findAll().get(4));
		model.addAttribute("menu6",menuRepository.findAll().get(5));
		model.addAttribute("menu7",menuRepository.findAll().get(6));
		model.addAttribute("menu8",menuRepository.findAll().get(7));
		model.addAttribute("menu9",menuRepository.findAll().get(8));
		model.addAttribute("menu10",menuRepository.findAll().get(9));
		return "public-restaurant";
	}
	@RequestMapping("public-client")
	public String publicClient(Model model) {
		ArrayList<User> users=new ArrayList<User>();
		users = (ArrayList<User>) userRepository.findAll();
		model.addAttribute("username",users.get(0).getName()) ;
		model.addAttribute("useremail",users.get(0).getEmail());
		model.addAttribute("userage",users.get(0).getAge());
		model.addAttribute("userdescription",users.get(0).getDescription());
		return "public-client";
	}
	@RequestMapping("search-web")
	public String searchWeb(Model model) {

		return "search-web";
	}

	
	
}
