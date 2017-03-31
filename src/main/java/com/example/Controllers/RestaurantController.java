package com.example.Controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Entities.Booking;
import com.example.Entities.Menu;
import com.example.Entities.Restaurant;
import com.example.Entities.Review;
import com.example.Entities.Voucher;
import com.example.Services.RestaurantService;

@Controller
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;

	@RequestMapping("/public-restaurant/{name}")
	public String publicRestaurant(Model model,HttpServletRequest request,Authentication authentication, @PathVariable String name,
			@RequestParam(required=false) String unfavPulsed,@RequestParam(required=false) String favPulsed) {
		String fileName = "profileImageRestaurant"+restaurantService.restaurantServiceFindByName(name).getId()+".jpg";
		model.addAttribute("fileName", fileName);
		model.addAttribute("inSession", (request.isUserInRole("USER")||request.isUserInRole("RESTAURANT")));
		Page<Menu> menu=restaurantService.restaurantServiceFindByRestaurantMenu(restaurantService.restaurantServiceFindByName(name),new PageRequest(0,5));
		model.addAttribute("BigMenu", menu.getNumberOfElements()>4);
		model.addAttribute("restaurantId", restaurantService.restaurantServiceFindByName(name).getId());
		model.addAttribute("restaurant", restaurantService.restaurantServiceFindByName(name));
		model.addAttribute("menu", restaurantService.restaurantServiceFindByRestaurantMenu(restaurantService.restaurantServiceFindByName(name),new PageRequest(0,4)));
		model.addAttribute("vouchers", restaurantService.restaurantServiceFindByName(name).getVouchers());
		model.addAttribute("reviews", restaurantService.restaurantServiceFindByName(name).getRestaurantReviews());
		model.addAttribute("inSession", request.isUserInRole("USER"));
		model.addAttribute("outSession", !request.isUserInRole("USER"));
		if (request.isUserInRole("USER")) {
			String userloggin = authentication.getName();
			model.addAttribute("favButton", ! restaurantService.userRepositoryfindByEmail(userloggin).getRestaurants()
					.contains(restaurantService.restaurantServiceFindByName(name)));
			model.addAttribute("unfavButton", restaurantService.userRepositoryfindByEmail(userloggin).getRestaurants()
					.contains(restaurantService.restaurantServiceFindByName(name)));
			if (favPulsed != null) {

				restaurantService.userRepositoryfindByEmail(userloggin).getRestaurants().add(restaurantService.restaurantServiceFindByName(name));
				restaurantService.userRepositorysave(restaurantService.userRepositoryfindByEmail(userloggin));
				restaurantService.restaurantServiceFindByName(name).getUsers().add(restaurantService.userRepositoryfindByEmail(userloggin));
				restaurantService.restaurantServiceSave(restaurantService.restaurantServiceFindByName(name));

			}
			model.addAttribute("favButton", !restaurantService.userRepositoryfindByEmail(userloggin).getRestaurants()
					.contains(restaurantService.restaurantServiceFindByName(name)));
			model.addAttribute("unfavButton",restaurantService.userRepositoryfindByEmail(userloggin).getRestaurants()
					.contains(restaurantService.restaurantServiceFindByName(name)));
			if (unfavPulsed != null) {

				restaurantService.userRepositoryfindByEmail(userloggin).getRestaurants().remove(restaurantService.restaurantServiceFindByName(name));
				restaurantService.userRepositorysave(restaurantService.userRepositoryfindByEmail(userloggin));
				restaurantService.restaurantServiceFindByName(name).getUsers().remove(restaurantService.userRepositoryfindByEmail(userloggin));
				restaurantService.restaurantServiceSave(restaurantService.restaurantServiceFindByName(name));

			}
			model.addAttribute("favButton", !restaurantService.userRepositoryfindByEmail(userloggin).getRestaurants()
					.contains(restaurantService.restaurantServiceFindByName(name)));
			model.addAttribute("unfavButton", restaurantService.userRepositoryfindByEmail(userloggin).getRestaurants()
					.contains(restaurantService.restaurantServiceFindByName(name)));
		}
		return "public-restaurant";
	}

	@RequestMapping("/private-restaurant/")
	public String privateRestaurant(Model model, HttpServletRequest request,Authentication authentication) throws ParseException {
		try{
			String restaurantloggin = authentication.getName();
			String fileName = "profileImageRestaurant" + restaurantService.restaurantRepositoryFindByEmail(restaurantloggin).getId()
					+ ".jpg";
			model.addAttribute("fileName", fileName);
			String fileMenuName = "menuImageRestaurant"
					+restaurantService.restaurantRepositoryFindByEmail(restaurantloggin).getId()+ (restaurantService.restaurantRepositoryFindByEmail(restaurantloggin).getMenus().size() + 1) + ".jpg";
			model.addAttribute("fileMenuName", fileMenuName);
			if (request.isUserInRole("RESTAURANT")) {
				model.addAttribute("restaurantId", restaurantService.restaurantRepositoryFindByEmail(restaurantloggin).getId());
				Page<Menu> menus=restaurantService.menuRepositoryfindByRestaurantMenu(restaurantService.restaurantServiceFindByName(restaurantService.restaurantRepositoryFindByEmail(restaurantloggin).getName()),new PageRequest(0,5));
				model.addAttribute("BigMenu", menus.getNumberOfElements()>4);
				model.addAttribute("restaurant", restaurantService.restaurantRepositoryFindByEmail(restaurantloggin));
				model.addAttribute("menu",restaurantService.menuRepositoryfindByRestaurantMenu(restaurantService.restaurantServiceFindByName(restaurantService.restaurantRepositoryFindByEmail(restaurantloggin).getName()),new PageRequest(0,4)));
				model.addAttribute("bookings", restaurantService.restaurantRepositoryFindByEmail(restaurantloggin).getBookings());
				model.addAttribute("bookingsAccepted",restaurantService.bookingRepositoryfindByStateAndBookingRestaurant("Accepted",
						restaurantService.restaurantRepositoryFindByEmail(restaurantloggin)));
				model.addAttribute("bookingsInProcess", restaurantService.bookingRepositoryfindByStateAndBookingRestaurant("In Process",
						restaurantService.restaurantRepositoryFindByEmail(restaurantloggin)));
				model.addAttribute("vouchers", restaurantService.restaurantRepositoryFindByEmail(restaurantloggin).getVouchers());
				model.addAttribute("reviews",
						restaurantService.restaurantRepositoryFindByEmail(restaurantloggin).getRestaurantReviews());
			}
		} catch (NullPointerException ex) {
			ex.printStackTrace();

		}
		return "private-restaurant";
	}
	
	@RequestMapping(value = "/accept-reservation/", method = RequestMethod.POST)
	public String acceptReservation(Model model,@RequestParam(required=false)String acceptPulsed,@RequestParam(required=false) Long acceptPulsedID){
		if (acceptPulsed != null) {
			Booking booking = restaurantService.bookingRepositoryfindById(acceptPulsedID);
			booking.setState("Accepted");
			restaurantService.bookingRepositorysave(booking);
		}
		return "redirect:/private-restaurant/";
	}
	
	@RequestMapping(value = "/public-restaurant/{name}/book", method = RequestMethod.POST)
	public String bookTable(Model model,@RequestParam(required=false) String bookingday,
			@RequestParam(required=false) String bookinghour,@RequestParam(required=false) String guests,
			@RequestParam(required=false) String specialRequirements,@PathVariable String name){
		Date date=new Date();
		try {
			date = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse("2017-03-" + bookingday + " " + bookinghour);
		} catch (ParseException e) {
			return null;
		}
		Booking booking = new Booking(date, Integer.parseInt(guests), specialRequirements);
		booking.setBookingRestaurant(restaurantService.restaurantServiceFindByName(name));
		long id = 1;
		booking.setBookingUser(restaurantService.userRepositoryfindOne(id));
		restaurantService.bookingRepositorysave(booking);
		return "redirect:/public-restaurant/{name}";
	}
	
	@RequestMapping(value = "/public-restaurant/{name}/review", method = RequestMethod.POST)
	public String writeReview(Model model,HttpServletRequest request,Authentication authentication,@PathVariable String name,
			@RequestParam(required=false) String rate, @RequestParam(required=false) String content){
		if(request.isUserInRole("USER")){
			String userloggin = authentication.getName();
			if (rate!=null) {
				Review review = new Review(content,Integer.parseInt(rate),new Date());
				review.setReviewRestaurant(restaurantService.restaurantServiceFindByName(name));
				review.setReviewUser(restaurantService.userRepositoryfindByEmail(userloggin));
				restaurantService.reviewRepositorysave(review);
			}
		}
		return "redirect:/public-restaurant/{name}";
	}

	@RequestMapping(value = "/private-restaurant/modify", method = RequestMethod.POST)
	public String modifyRestaurant(Model model, @RequestParam(required=false)String namerest,
			@RequestParam(required=false)String location, @RequestParam(required=false)Integer telephone,
			@RequestParam(required=false)String descriptionrest,@RequestParam(required=false)String emailrest,@RequestParam(required=false)Boolean Dinner,
			@RequestParam(required=false)String pwd,@RequestParam(required=false)String confirmpwd,Authentication authentication,
			@RequestParam(required=false)Boolean Breakfast,@RequestParam(required=false)Boolean Lunch,HttpServletRequest request){
		if (request.isUserInRole("RESTAURANT")) {
			if (namerest != null) {
				Restaurant restaurant = restaurantService.restaurantRepositoryFindByEmail(authentication.getName());
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
				restaurantService.restaurantServiceSave(restaurant);

			}
		}
		return "redirect:/private-restaurant/";
	}
	
	@RequestMapping(value = "/private-restaurant/voucher", method = RequestMethod.POST)
	public String addVoucher(Model model, @RequestParam(required=false) String vouchername,
			@RequestParam(required=false) String voucherdescription,HttpServletRequest request,@RequestParam(required=false) Integer expiry,
			@RequestParam(required=false) Integer max, @RequestParam(required=false) Integer min,Authentication authentication){
		if (request.isUserInRole("RESTAURANT")) {
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
				voucher.setVoucherUsers(restaurantService.userRepositoryfindByAgeBetween(min, max));
				voucher.setRestaurant(restaurantService.restaurantRepositoryFindByEmail(authentication.getName()));
				restaurantService.voucherRepositorysave(voucher);
			}
		}
		return "redirect:/private-restaurant/";
	}
	
	@RequestMapping(value = "/private-restaurant/menu", method = RequestMethod.POST)
	public String addMenu(Model model, @RequestParam(required=false) String vouchername,
			@RequestParam(required=false) String voucherdescription,HttpServletRequest request,@RequestParam(required=false) String type,
			@RequestParam(required=false) String menudescription,@RequestParam(required=false) String menuname,
			@RequestParam(required=false) Double menuprice,Authentication authentication){
		if (request.isUserInRole("RESTAURANT")) {
			if (menuname != null) {
				boolean repeated = false;
				Menu menu = new Menu(menuname, menuprice, menudescription);
				for(Menu m: restaurantService.restaurantRepositoryFindByEmail(authentication.getName()).getMenus()){
					if(m.getDish().equals(menuname)){
						repeated=true;
						break;
					}
				}
				if (!repeated) {
					menu.setRestaurantMenu(restaurantService.restaurantRepositoryFindByEmail(authentication.getName()));
					restaurantService.restaurantServiceMenuSave(menu);
				}
			}
		}
		return "redirect:/private-restaurant/";
	}
	
}
