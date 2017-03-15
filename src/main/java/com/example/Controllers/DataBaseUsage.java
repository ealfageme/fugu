package com.example.Controllers;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import com.example.Entities.*;
import com.example.Repositories.*;

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
	
	}
	@PostConstruct
    private void initDatabase() {
    
		City city1 = new City("Madrid");
		City city2 = new City("Barcelona");
		City city3 = new City("Valencia");
		City city4 = new City("Sevilla");
		City city5 = new City("Zaragoza");
		City city6 = new City("Bilbao");
		cityRepository.save(city1);
		cityRepository.save(city2);
		cityRepository.save(city3);
		cityRepository.save(city4);
		cityRepository.save(city5);
		cityRepository.save(city6);
		
		Restaurant rest1 = new Restaurant ("American Whey","Avenida España 43 ","Description","american@whey.com","American",658742154, 5.0, 15.0,"password",true,true,true,"ROLE_RESTAURANT");
		Restaurant rest11 = new Restaurant ("American Whey2","Avenida España 43 ","Description","american@whey2.com","American",658742154, 5.0, 15.0,"password",true,true,true,"ROLE_RESTAURANT");
		Restaurant rest2 = new Restaurant ("Meson Mariano","Avenida America 2","Description","meson@mariano.com","tapas",652312342, 4.9, 12.0,"password",true,true,true,"ROLE_RESTAURANT");
		Restaurant rest3 = new Restaurant ("Meson Felipe","Avenida Barcelona 43","Description","meson@felipe.com","Galician",123213123, 3.5,5.0,"password",true,true,true,"ROLE_RESTAURANT");
		Restaurant rest4 = new Restaurant ("Meson Lito","Calle de la amagura 5","Description","meson@lito.com","Italian",123213123, 1.6,13.0,"password",true,true,true,"ROLE_RESTAURANT");
		Restaurant rest5 = new Restaurant ("Meson Daniel","Plaza de España 69","Description","meson@daniel.com","Japanese",123213123, 3.2,16.0,"password",true,true,true,"ROLE_RESTAURANT");
		Restaurant rest6 = new Restaurant ("Meson Eulalio","Calle Margaret 12","Description","meson@eulalio.com","Chinese",123213123, 4.8,18.0,"password",true,true,true,"ROLE_RESTAURANT");
		Restaurant rest7 = new Restaurant ("Meson Eusebio","Callejón Paco 1","Description","meson@eusebio.com","Mexican", 123213123,3.1,12.0,"password",true,true,true,"ROLE_RESTAURANT");
		Restaurant rest8 = new Restaurant ("Meson Gento","Calle azulona 76","Description","meson@gento.com","Indian",123213123, 3.5,15.0,"password",true,true,true,"ROLE_RESTAURANT");
		Restaurant rest9 = new Restaurant ("Meson Genaro","Plaza de la reina 3","Description","meson@genaro.com","Thai",123213123, 4.1,20.0,"password",true,true,true,"ROLE_RESTAURANT");
		Restaurant rest10 = new Restaurant ("Meson Agapito","Avenida Pablo","Description","meson@agapito.com","Galician",123213123,2.6,8.0,"password",true,true,true,"ROLE_RESTAURANT");
		
		rest1.setCity(city1);
		rest11.setCity(city3);
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
		restaurantRepository.save(rest11);
		
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
		Menu menu11 = new Menu("Beef Double Burger", 15.5," with BBQ");
		Menu menu12 = new Menu("Bulgarian Wings",3.5, "with sauce");
		Menu menu13 = new Menu("Chicken Finger Sandwich", 8.0,"with Salad" );

		menu1.setRestaurantMenu(rest1);
		menu2.setRestaurantMenu(rest1);
		menu3.setRestaurantMenu(rest4);
		menu4.setRestaurantMenu(rest4);
		menu5.setRestaurantMenu(rest5);
		menu6.setRestaurantMenu(rest6);
		menu7.setRestaurantMenu(rest7);
		menu8.setRestaurantMenu(rest8);
		menu9.setRestaurantMenu(rest9);
		menu10.setRestaurantMenu(rest10);
		menu11.setRestaurantMenu(rest2);
		menu12.setRestaurantMenu(rest2);
		menu13.setRestaurantMenu(rest3);
		
		
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
		menuRepository.save(menu11);
		menuRepository.save(menu12);
		menuRepository.save(menu13);

		User user1 = new User("John","john@website.com","Description","pass",21, "Italian","ROLE_USER");
		User user2 = new User("Peter","peter@website.com","Description","pass",31, "Tapas","ROLE_USER");
		User user3 = new User("Samuel","samuel@website.com","Description","pass",54, "Thai","ROLE_USER");	
		User user4 = new User("Lucas","lucas@website.com","Description","pass",69, "American","ROLE_USER");	

		
		user1.getRestaurant().add(rest1);
		user1.getRestaurant().add(rest2);
		user2.getRestaurant().add(rest1);
		user3.getRestaurant().add(rest2);
		user4.getRestaurant().add(rest2);
		user1.getRestaurant().add(rest4);
		user1.getRestaurant().add(rest5);
		user2.getRestaurant().add(rest6);
		user3.getRestaurant().add(rest7);
		user4.getRestaurant().add(rest8);
		user1.getRestaurant().add(rest9);
		user1.getRestaurant().add(rest8);
		user2.getRestaurant().add(rest10);
		user3.getRestaurant().add(rest10);
		user4.getRestaurant().add(rest1);
		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.save(user4);

		rest1.getUsers().add(user1);
		rest2.getUsers().add(user1);
		rest1.getUsers().add(user2);
		rest2.getUsers().add(user3);
		rest2.getUsers().add(user4);
		rest4.getUsers().add(user1);
		rest5.getUsers().add(user1);
		rest6.getUsers().add(user2);
		rest7.getUsers().add(user3);
		rest8.getUsers().add(user4);
		rest9.getUsers().add(user1);
		rest8.getUsers().add(user1);
		rest10.getUsers().add(user2);
		rest10.getUsers().add(user3);
		rest1.getUsers().add(user4);
		
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
		
		user3.getFollowing().add(user1);
		user3.getFollowing().add(user2);
		
		userRepository.save(user3);
		
		user1.getFollowing().add(user3);
		user1.getFollowing().add(user2);

		userRepository.save(user1);
		
		user2.getFollowing().add(user3);
		
		userRepository.save(user2);
		
		Voucher voucher1 = new Voucher("2*1 in salads","Come with a friend and eat for the half price",new Date());
		Voucher voucher2 = new Voucher("FREE drinks on fridays","Every friday we offer free drinks with hamburger menus",new Date());
		Voucher voucher3 = new Voucher("30% discount", "Available on mondays only", new Date());
		Voucher voucher4 = new Voucher("40% discount", "Available on tuesdays only",new Date());
		Voucher voucher5 = new Voucher("50% discount", "Available on thursdays only",new Date());
		Voucher voucher6 = new Voucher("10% discount", "Available on fridays only",new Date());
		voucher1.getVoucherUsers().add(user1);
		voucher2.getVoucherUsers().add(user1);
		voucher3.getVoucherUsers().add(user2);
		voucher4.getVoucherUsers().add(user2);
		voucher5.getVoucherUsers().add(user3);
		voucher6.getVoucherUsers().add(user3);
		voucher1.setRestaurant(rest1);
		voucher2.setRestaurant(rest2);
		voucher3.setRestaurant(rest3);
		voucher4.setRestaurant(rest4);
		voucher5.setRestaurant(rest5);
		voucher6.setRestaurant(rest6);
		voucherRepository.save(voucher1);
		voucherRepository.save(voucher2);
		voucherRepository.save(voucher3);
		voucherRepository.save(voucher4);
		voucherRepository.save(voucher5);
		voucherRepository.save(voucher6);
		
		//RELACION USER - REVIEW
		
		Review review1 = new Review("Fucking amazing", 5.0, new Date());
		Review review2 = new Review("This restaurant must be improved", 2.2, new Date());
		Review review3 = new Review("If you want to spend a good time eating, come here", 4.1, new Date());
		Review review4 = new Review("Cute decoration, friendly staff and a good cook, also dogs allowed My favourite restaurant for English Breakfast!!!", 5.0, new Date());
		Review review5 = new Review("This place have classic British dishes, served in a nery warm, homely, atmosphere. Love their calves' liver dish - it never fails. Welcoming staff and good service!", 2.2, new Date());
		Review review6 = new Review("Nice and near to Liverpool Street station. Lovely food with good service and the atmosphere is buzzing as it gets quite busy in the evening would definitely go again.", 4.1, new Date());
		review1.setUser(user1);
		review2.setUser(user2);
		review3.setUser(user3);
		review4.setUser(user1);
		review5.setUser(user2);
		review6.setUser(user3);
		review1.setReviewRestaurant(rest1);
		review2.setReviewRestaurant(rest1);
		review3.setReviewRestaurant(rest3);
		review4.setReviewRestaurant(rest4);
		review5.setReviewRestaurant(rest2);
		review6.setReviewRestaurant(rest5);
		reviewRepository.save(review1);
		reviewRepository.save(review2);
		reviewRepository.save(review3);
		reviewRepository.save(review4);
		reviewRepository.save(review5);
		reviewRepository.save(review6);

		
		//RELACION USER - BOOKING
		Booking booking1 = new Booking(new Date(), 5,"One high chair for the baby");
		Booking booking2 = new Booking(new Date(), 2,"Champagne");
		Booking booking3 = new Booking(new Date(), 3,"High chair");
		Booking booking4 = new Booking(new Date(), 4,"");
		Booking booking5 = new Booking(new Date(), 1,"");
		Booking booking6 = new Booking(new Date(), 6,"");
		booking1.setBookingUser(user1);
		booking2.setBookingUser(user3);
		booking3.setBookingUser(user1);
		booking4.setBookingUser(user3);
		booking5.setBookingUser(user2);
		booking6.setBookingUser(user2);
		booking1.setBookingRestaurant(rest1);
		booking2.setBookingRestaurant(rest3);
		booking3.setBookingRestaurant(rest2);
		booking4.setBookingRestaurant(rest4);
		booking5.setBookingRestaurant(rest5);
		booking6.setBookingRestaurant(rest5);
		
		bookingRepository.save(booking1);
		bookingRepository.save(booking2);
		bookingRepository.save(booking3);
		bookingRepository.save(booking4);
		bookingRepository.save(booking5);
		bookingRepository.save(booking6);
    	//restaurantRepository.save(new Restaurant ("American Whey","Avenida España 43 ","Description","american@whey.com","American",658742154, 5.0, 15.0,"password",true,true,true,"ROLE_RESTAURANT"+"American Whey"));
    }

	
	
}
