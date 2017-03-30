package com.example.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.Entities.Booking;
import com.example.Entities.Menu;
import com.example.Entities.Restaurant;
import com.example.Entities.Review;
import com.example.Entities.User;
import com.example.Entities.Voucher;
import com.example.Repositories.BookingRepository;
import com.example.Repositories.MenuRepository;
import com.example.Repositories.RestaurantRepository;
import com.example.Repositories.ReviewRepository;
import com.example.Repositories.UserRepository;
import com.example.Repositories.VoucherRepository;


@Service
public class RestaurantService {
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
	
	public Restaurant restaurantServiceFindOne(Long id){
		return restaurantRepository.findOne(id);
	}
	public void restaurantServiceSave(Restaurant restaurant){
		restaurantRepository.save(restaurant);
	}
	public Restaurant restaurantServiceFindByName(String name){
		return restaurantRepository.findByName(name);
	}
	public Page<Restaurant> restaurantServicefindByRateBetweenOrderByRateDesc(Double min, Double max, Pageable page){
		return restaurantRepository.findByRateBetweenOrderByRateDesc(min, max, page);
	}
	public Page<Menu> restaurantServiceFindByRestaurantMenu(Restaurant restaurant, Pageable page){
		return menuRepository.findByRestaurantMenu(restaurant, page);
	}
	public Menu restaurantServiceMenuFindDish(String dish){
		return menuRepository.findByDish(dish);
	}
	public void restaurantServiceMenuSave (Menu menu){
		menuRepository.save(menu);
	}
	public void restaurantServiceDelete(Long id){
		restaurantRepository.delete(id);
	}
	public Page<Review> reviewRepositoryfindByReviewRestaurant(Restaurant restaurant,Pageable page){
		return reviewRepository.findByReviewRestaurant(restaurant, page);
	}
	public void reviewRepositorysave(Review newReview){
		 reviewRepository.save(newReview);
	}
	public Review reviewRepositoryfindByContent(String content){
		return reviewRepository.findByContent(content);
	}
	public void bookingRepositorysave(Booking booking){
		bookingRepository.save(booking);
	}
	public Page<Voucher> voucherRepositoryfindByRestaurant(Restaurant restaurant,Pageable page){
		return voucherRepository.findByRestaurant(restaurant, page);
	}
	public User userRepositoryfindByEmail(String email){
		return userRepository.findByEmail(email);
	}
	public void userRepositorysave(User user){
		userRepository.save(user);
	}
	public void voucherRepositorysave(Voucher voucher){
			voucherRepository.save(voucher);
	}
	public Voucher voucherRepositoryfindByName(String name){
		return voucherRepository.findByName(name);
	}
	public Page<Booking> bookingRepositoryfindByBookingRestaurant(Restaurant restaurant, Pageable page){
		return bookingRepository.findByBookingRestaurant(restaurant,page);
	}
	public Booking bookingRepositoryfindBySpecialRequirements(String special){
		return bookingRepository.findBySpecialRequirements(special);
	}
	public Restaurant restaurantServiceFindByEmail(String email){
		return restaurantRepository.findByEmail(email);
	}
}
