package com.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FuguController {

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

	@RequestMapping("/main/")
	public String main(Model model, Restaurant restaurant, @RequestParam(required=false) String restaurantname, 
			 @RequestParam(required=false) String address,@RequestParam(required=false) String kindoffood,
			 @RequestParam(required=false) String email,@RequestParam(required=false) String password,
			 @RequestParam(required=false) String name,@RequestParam(required=false) String useremail,
			 @RequestParam(required=false) String userpassword,@RequestParam(required=false) String favouritefood) {	
		model.addAttribute("restaurant", restaurantRepository.findAll(new Sort(new Order(Sort.Direction.DESC, "rate"))));
		if (restaurantname!=null){
		Restaurant rest= new Restaurant (restaurantname,address,"",email,kindoffood,0, 0, 0,password);
		restaurantRepository.save(rest);}
		if (name!=null){
		User user = new User(name,useremail,"", userpassword ,18,favouritefood);
		userRepository.save(user);}
		return "main";
	}
	

	@RequestMapping("/city/{name}")
	public String city(Model model, @PathVariable String name) {
		model.addAttribute("city", cityRepository.findByName(name));
		model.addAttribute("restaurants", cityRepository.findByName(name).getCityResturants());

		return "city";
	}

	@RequestMapping("/private-client/{name}")
	public String privateClient(Model model, @PathVariable String name,@RequestParam(required=false) String username,
			@RequestParam(required=false) String useremail,@RequestParam(required=false) String userdescription,
			@RequestParam(required=false) String favouritefood, @RequestParam(required=false) String password,
			@RequestParam(required=false) String confirmpassword) {

		model.addAttribute("user", userRepository.findByName(name));
		model.addAttribute("restaurants", userRepository.findByName(name).getRestaurants());
		model.addAttribute("following", userRepository.findByName(name).getFollowing());
		model.addAttribute("bookings", userRepository.findByName(name).getBookings());
		model.addAttribute("vouchers", userRepository.findByName(name).getUserVouchers());
		model.addAttribute("reviews", userRepository.findByName(name).getReviews());
		model.addAttribute("generalRestaurants", restaurantRepository.findAll());
		
		if (username!=null) {
			User user = userRepository.findByName(name);
			user.setName(username);
			user.setEmail(useremail);
			user.setDescription(userdescription);
			user.setFavouriteFood(favouritefood);
			if (password.equals(confirmpassword)){
				user.setPassword(password);
			}
			userRepository.save(user);
		}
		return "private-client";
	}

	@RequestMapping("/public-restaurant/{name}")
	public String publicRestaurant(Model model, @PathVariable String name,@RequestParam(required=false) String bookingday,
			@RequestParam(required=false) String bookinghour,@RequestParam(required=false) String guests,@RequestParam(required=false) String specialRequirements) {
		if(bookingday!=null && bookinghour!=null){
			System.out.println(bookingday+" "+bookinghour);
			Date date=new Date();
			try {
				date= new SimpleDateFormat("yyyy-MM-dd hh:mm").parse("2017-03-"+bookingday+" "+bookinghour);
		     } catch (ParseException e) {
		         return null;
		     }
			Booking booking=new Booking(date,Integer.parseInt(guests),specialRequirements);
			booking.setBookingRestaurant(restaurantRepository.findByName(name));
			long id=1;
			booking.setBookingUser(userRepository.findOne(id));
			bookingRepository.save(booking);
		}	
		model.addAttribute("restaurant", restaurantRepository.findByName(name));
		model.addAttribute("menu", restaurantRepository.findByName(name).getMenus());
		model.addAttribute("vouchers", restaurantRepository.findByName(name).getVouchers());
		model.addAttribute("reviews", restaurantRepository.findByName(name).getRestaurantReviews());
	
		return "public-restaurant";
	}

	@RequestMapping("/public-client/{name}")
	public String publicClient(Model model, @PathVariable String name) {
		model.addAttribute("user", userRepository.findByName(name));
		model.addAttribute("restaurants", userRepository.findByName(name).getRestaurants());
		model.addAttribute("following", userRepository.findByName(name).getFollowing());
		model.addAttribute("bookings", userRepository.findByName(name).getBookings());
		model.addAttribute("vouchers", userRepository.findByName(name).getUserVouchers());
		model.addAttribute("reviews", userRepository.findByName(name).getReviews());
		model.addAttribute("generalRestaurants", restaurantRepository.findAll());
		return "public-client";
	}

	@RequestMapping("/private-restaurant/{name}")
	public String privateRestaurant(Model model, @PathVariable String name, @RequestParam(required=false) String type,
	@RequestParam(required=false) Integer max, @RequestParam(required=false) Integer min,
	@RequestParam(required=false) String vouchername, @RequestParam(required=false) String voucherdescription,
	@RequestParam(required=false) String menudescription,@RequestParam(required=false) String menuname,
	@RequestParam(required=false) Double menuprice, @RequestParam(required=false)String namerest,
	@RequestParam(required=false)String location, @RequestParam(required=false)Integer telephone,
	@RequestParam(required=false)String descriptionrest,@RequestParam(required=false)String emailrest,
	@RequestParam(required=false)String pwd,@RequestParam(required=false)String confirmpwd) {
		model.addAttribute("restaurant", restaurantRepository.findByName(name));
		model.addAttribute("menu", restaurantRepository.findByName(name).getMenus());
		model.addAttribute("bookings", restaurantRepository.findByName(name).getBookings());
		model.addAttribute("vouchers", restaurantRepository.findByName(name).getVouchers());
		model.addAttribute("reviews", restaurantRepository.findByName(name).getRestaurantReviews());
		if (namerest!=null){
			Restaurant restaurant = restaurantRepository.findByName(name);
			restaurant.setName(namerest);
			restaurant.setAddress(location);
			restaurant.setDescription(descriptionrest);
			restaurant.setPhone(telephone);
			restaurant.setEmail(emailrest);
			if (pwd.equals(confirmpwd)){
				restaurant.setPassword(pwd);
			}
			restaurantRepository.save(restaurant);
		}
		if (vouchername!=null){
			Voucher voucher= new Voucher(vouchername,voucherdescription,new Date());
			voucher.setVoucherUsers(userRepository.findByAgeBetween(min,max));
			voucher.setRestaurant(restaurantRepository.findByName(name));
			voucherRepository.save(voucher);}
		if (menuname!=null){
			Menu menu= new Menu(menuname,menuprice,menudescription);
			menu.setRestaurantMenu(restaurantRepository.findByName(name));
			menuRepository.save(menu);}
		return "private-restaurant";
	}

	@RequestMapping("/search-web/")
	public String searchWeb(Model model, @RequestParam(required=false) String name, @RequestParam(required=false) String city, @RequestParam(required=false) String foodType, @RequestParam(required=false) Double min, @RequestParam(required=false) Double max, @RequestParam(required=false) Double minPrice, @RequestParam(required=false) Double maxPrice) {
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
		
		return "search-web";
	}

	
}