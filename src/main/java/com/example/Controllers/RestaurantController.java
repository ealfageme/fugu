package com.example.Controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.security.core.Authentication;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.Entities.Booking;
import com.example.Entities.Menu;
import com.example.Entities.Restaurant;
import com.example.Entities.Review;
import com.example.Entities.Voucher;
import com.example.Repositories.BookingRepository;
import com.example.Repositories.MenuRepository;
import com.example.Repositories.RestaurantRepository;
import com.example.Repositories.ReviewRepository;
import com.example.Repositories.UserRepository;
import com.example.Repositories.VoucherRepository;
import com.fasterxml.jackson.annotation.JsonView;

@Controller
public class RestaurantController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RestaurantRepository restaurantRepository;
	@Autowired
	private BookingRepository bookingRepository;
	@Autowired
	private ReviewRepository reviewRepository;
	@Autowired
	private MenuRepository menuRepository;
	@Autowired
	private VoucherRepository voucherRepository;

	@ResponseBody
	@RequestMapping(value = "/restaurants/{id}", method = RequestMethod.GET)
	public ResponseEntity<Restaurant> getRestaurant(HttpSession session, @PathVariable long id) {
		session.setMaxInactiveInterval(-1);
		return new ResponseEntity<>(restaurantRepository.findOne(id), HttpStatus.OK);
	}

	@RequestMapping(value="/restaurants/{id}/menus", method=RequestMethod.GET)
	public ResponseEntity<Page<Menu>> getRestaurantMenus(HttpSession session, @PathVariable long id, Pageable page) {
		session.setMaxInactiveInterval(-1);
		Restaurant restaurant=restaurantRepository.findOne(id);
		return new ResponseEntity<>(menuRepository.findByRestaurantMenu(restaurant,page), HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value="/restaurants/", method=RequestMethod.GET)
	public ResponseEntity<Page<Restaurant>> getRestaurants(HttpSession session, Pageable page) {
		session.setMaxInactiveInterval(10);

		return new ResponseEntity<>(
				restaurantRepository.findByRateBetweenOrderByRateDesc(new Double(0.0), new Double(5.0), page),
				HttpStatus.OK);
	}

	@JsonView(Restaurant.Basic.class)
	@RequestMapping("/public-restaurant/{name}")
	public String publicRestaurant(Model model,HttpServletRequest request,Authentication authentication, @PathVariable String name,@RequestParam(required=false) String bookingday,
			@RequestParam(required=false) String bookinghour,@RequestParam(required=false) String guests,
			@RequestParam(required=false) String specialRequirements,
			@RequestParam(required=false) String rate, @RequestParam(required=false) String content,
			@RequestParam(required=false) String unfavPulsed,@RequestParam(required=false) String favPulsed) {
		String fileName = "profileImageRestaurant"+restaurantRepository.findByName(name).getId()+".jpg";
		model.addAttribute("fileName", fileName);
		model.addAttribute("inSession", (request.isUserInRole("USER")||request.isUserInRole("RESTAURANT")));
		Page<Menu> menu=menuRepository.findByRestaurantMenu(restaurantRepository.findByName(name),new PageRequest(0,5));
		model.addAttribute("BigMenu", menu.getNumberOfElements()>4);
		if(bookingday!=null && bookinghour!=null){
			System.out.println(bookingday+" "+bookinghour);
			Date date=new Date();
			try {
				date = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse("2017-03-" + bookingday + " " + bookinghour);
			} catch (ParseException e) {
				return null;
			}
			Booking booking = new Booking(date, Integer.parseInt(guests), specialRequirements);
			booking.setBookingRestaurant(restaurantRepository.findByName(name));
			long id = 1;
			booking.setBookingUser(userRepository.findOne(id));
			bookingRepository.save(booking);
		}
		model.addAttribute("restaurantId", restaurantRepository.findByName(name).getId());
		model.addAttribute("restaurant", restaurantRepository.findByName(name));
		model.addAttribute("menu", menuRepository.findByRestaurantMenu(restaurantRepository.findByName(name),new PageRequest(0,4)));
		model.addAttribute("vouchers", restaurantRepository.findByName(name).getVouchers());
		model.addAttribute("reviews", restaurantRepository.findByName(name).getRestaurantReviews());

		model.addAttribute("inSession", request.isUserInRole("USER"));
		model.addAttribute("outSession", !request.isUserInRole("USER"));
		if(request.isUserInRole("USER")){
			String userloggin = authentication.getName();
			if (rate!=null) {
				Review review = new Review(content,Integer.parseInt(rate),new Date());
				review.setReviewRestaurant(restaurantRepository.findByName(name));
				review.setReviewUser(userRepository.findByEmail(userloggin));
				reviewRepository.save(review);
			}
		}
		if (request.isUserInRole("USER")) {
			String userloggin = authentication.getName();
			model.addAttribute("favButton", !userRepository.findByEmail(userloggin).getRestaurants()
					.contains(restaurantRepository.findByName(name)));
			model.addAttribute("unfavButton", userRepository.findByEmail(userloggin).getRestaurants()
					.contains(restaurantRepository.findByName(name)));
			if (favPulsed != null) {

				userRepository.findByEmail(userloggin).getRestaurants().add(restaurantRepository.findByName(name));
				userRepository.save(userRepository.findByEmail(userloggin));
				restaurantRepository.findByName(name).getUsers().add(userRepository.findByEmail(userloggin));
				restaurantRepository.save(restaurantRepository.findByName(name));

			}
			model.addAttribute("favButton", !userRepository.findByEmail(userloggin).getRestaurants()
					.contains(restaurantRepository.findByName(name)));
			model.addAttribute("unfavButton", userRepository.findByEmail(userloggin).getRestaurants()
					.contains(restaurantRepository.findByName(name)));
			if (unfavPulsed != null) {

				userRepository.findByEmail(userloggin).getRestaurants().remove(restaurantRepository.findByName(name));
				userRepository.save(userRepository.findByEmail(userloggin));
				restaurantRepository.findByName(name).getUsers().remove(userRepository.findByEmail(userloggin));
				restaurantRepository.save(restaurantRepository.findByName(name));

			}
			model.addAttribute("favButton", !userRepository.findByEmail(userloggin).getRestaurants()
					.contains(restaurantRepository.findByName(name)));
			model.addAttribute("unfavButton", userRepository.findByEmail(userloggin).getRestaurants()
					.contains(restaurantRepository.findByName(name)));
		}
		return "public-restaurant";
	}

	@JsonView(Restaurant.Basic.class)
	@RequestMapping("/private-restaurant/")
	public String privateRestaurant(Model model, HttpServletRequest request,Authentication authentication, @RequestParam(required=false) String type,
	@RequestParam(required=false) Integer max, @RequestParam(required=false) Integer min,
	@RequestParam(required=false) String vouchername, @RequestParam(required=false) String voucherdescription,
	@RequestParam(required=false) String menudescription,@RequestParam(required=false) String menuname,
	@RequestParam(required=false) Double menuprice, @RequestParam(required=false)String namerest,
	@RequestParam(required=false)String location, @RequestParam(required=false)Integer telephone,
	@RequestParam(required=false)String descriptionrest,@RequestParam(required=false)String emailrest,
	@RequestParam(required=false)String pwd,@RequestParam(required=false)String confirmpwd,
	@RequestParam(required=false)Boolean Breakfast,@RequestParam(required=false)Boolean Lunch,Pageable page,
	@RequestParam(required=false)Boolean Dinner,@RequestParam(required=false) Integer expiry) throws ParseException {
		try{
			String restaurantloggin = authentication.getName();
			String fileName = "profileImageRestaurant" + restaurantRepository.findByEmail(restaurantloggin).getId()
					+ ".jpg";
			model.addAttribute("fileName", fileName);
			String fileMenuName = "menuImageRestaurant"
					+restaurantRepository.findByEmail(restaurantloggin).getId()+ (restaurantRepository.findByEmail(restaurantloggin).getMenus().size() + 1) + ".jpg";
			model.addAttribute("fileMenuName", fileMenuName);
			if (request.isUserInRole("RESTAURANT")) {
				model.addAttribute("restaurantId", restaurantRepository.findByEmail(restaurantloggin).getId());
				Page<Menu> menus=menuRepository.findByRestaurantMenu(restaurantRepository.findByName(restaurantRepository.findByEmail(restaurantloggin).getName()),new PageRequest(0,5));
				model.addAttribute("BigMenu", menus.getNumberOfElements()>4);
				model.addAttribute("restaurant", restaurantRepository.findByEmail(restaurantloggin));
				model.addAttribute("menu", menuRepository.findByRestaurantMenu(restaurantRepository.findByName(restaurantRepository.findByEmail(restaurantloggin).getName()),new PageRequest(0,4)));
				model.addAttribute("bookings", restaurantRepository.findByEmail(restaurantloggin).getBookings());
				model.addAttribute("bookingsAccepted", bookingRepository.findByStateAndBookingRestaurant("Accepted",
						restaurantRepository.findByEmail(restaurantloggin)));
				model.addAttribute("bookingsInProcess", bookingRepository.findByStateAndBookingRestaurant("In Process",
						restaurantRepository.findByEmail(restaurantloggin)));
				model.addAttribute("vouchers", restaurantRepository.findByEmail(restaurantloggin).getVouchers());
				model.addAttribute("reviews",
						restaurantRepository.findByEmail(restaurantloggin).getRestaurantReviews());
				if (namerest != null) {
					Restaurant restaurant = restaurantRepository.findByEmail(restaurantloggin);
					restaurant.setName(namerest);
					restaurant.setAddress(location);
					restaurant.setDescription(descriptionrest);
					restaurant.setPhone(telephone);
					restaurant.setEmail(emailrest);
					if (pwd.equals(confirmpwd)) {
						restaurant.setPassword(pwd);
					}
					if (Breakfast != null)
						restaurant.setBreakfast(true);
					else
						restaurant.setBreakfast(false);
					if (Lunch != null)
						restaurant.setLunch(true);
					else
						restaurant.setLunch(false);
					if (Dinner != null)
						restaurant.setDinner(true);
					else
						restaurant.setDinner(false);
					restaurantRepository.save(restaurant);

				}
				if (vouchername != null) {
					 Calendar calendar = Calendar.getInstance();
				     calendar.setTime(new Date());
				     if(expiry==1){
					       calendar.add(Calendar.DAY_OF_YEAR, 7);
				}else if(expiry==2){
				       calendar.add(Calendar.DAY_OF_YEAR, 14);
				}else if(expiry==3){
						calendar.add(Calendar.DAY_OF_YEAR, 21);
					}else if(expiry==4){
						       calendar.add(Calendar.MONTH, 1);
					}else if(expiry==5){
						calendar.add(Calendar.MONTH, 2);
					}else if(expiry==6){
						calendar.add(Calendar.MONTH, 3);
					}else if(expiry==7){
						calendar.add(Calendar.MONTH, 6);
					}
					Voucher voucher = new Voucher(vouchername, voucherdescription,  calendar.getTime());					
					voucher.setVoucherUsers(userRepository.findByAgeBetween(min, max));
					voucher.setRestaurant(restaurantRepository.findByEmail(restaurantloggin));
					voucherRepository.save(voucher);
				}
				
				if (menuname != null) {
					boolean repeated = false;
					Menu menu = new Menu(menuname, menuprice, menudescription);
					for(Menu m: restaurantRepository.findByEmail(restaurantloggin).getMenus()){
						if(m.getDish().equals(menuname)){
							repeated=true;
							break;
						}
					}
					if (!repeated) {
						menu.setRestaurantMenu(restaurantRepository.findByEmail(restaurantloggin));
						menuRepository.save(menu);
					}
				}

			}
		} catch (NullPointerException ex) {
			ex.printStackTrace();

		}
		return "private-restaurant";
	}
	
	@RequestMapping(value = "/accept-reservation/", method = RequestMethod.POST)
	public String acceptReservation(Model model,@RequestParam(required=false)String acceptPulsed,@RequestParam(required=false) Long acceptPulsedID){
		if (acceptPulsed != null) {
			Booking booking = bookingRepository.findById(acceptPulsedID);
			booking.setState("Accepted");
			bookingRepository.save(booking);
		}
		return "redirect:/private-restaurant/";
	}

	
}
