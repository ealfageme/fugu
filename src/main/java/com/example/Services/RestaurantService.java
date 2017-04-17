package com.example.Services;

import java.util.List;

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
	
	//Restaurant
	public Restaurant restaurantRepositoryFindOne(Long id){
		return restaurantRepository.findOne(id);
	}
	public void restaurantRepositorySave(Restaurant restaurant){
		restaurantRepository.save(restaurant);
	}
	public Restaurant restaurantRepositoryFindByName(String name){
		return restaurantRepository.findByName(name);
	}
	public Restaurant restaurantRepositoryFindByEmail(String email){
		return restaurantRepository.findByEmail(email);
	}
	public List<Restaurant> restaurantRepositoryFindByCity(String city){
		return restaurantRepository.findByCityName(city);
	}
	public Page<Restaurant> restaurantRepositoryfindByRateBetweenOrderByRateDesc(Double min, Double max, Pageable page){
		return restaurantRepository.findByRateBetweenOrderByRateDesc(min, max, page);
	}
	public void restaurantRepositoryDelete(Long id){
		restaurantRepository.delete(id);
	}
	//Menu
	public Page<Menu> restaurantServiceFindByRestaurantMenu(Restaurant restaurant, Pageable page){
		return menuRepository.findByRestaurantMenu(restaurant, page);
	}
	public Menu restaurantServiceMenuFindDish(String dish){
		return menuRepository.findByDish(dish);
	}
	public void restaurantServiceMenuSave (Menu menu){
		menuRepository.save(menu);
	} 
	public Page<Menu> menuRepositoryfindByRestaurantMenu(Restaurant restaurant,Pageable page){
		return menuRepository.findByRestaurantMenu(restaurant,page);
	}
	//Review
	public Page<Review> reviewRepositoryfindByReviewRestaurant(Restaurant restaurant,Pageable page){
		return reviewRepository.findByReviewRestaurant(restaurant, page);
	}
	public void reviewRepositorysave(Review newReview){
		 reviewRepository.save(newReview);
	}
	public Review reviewRepositoryfindByContent(String content){
		return reviewRepository.findByContent(content);
	}
	//booking
	public void bookingRepositorysave(Booking booking){
		bookingRepository.save(booking);
	}
	public Page<Booking> bookingRepositoryfindByBookingRestaurant(Restaurant restaurant, Pageable page){
		return bookingRepository.findByBookingRestaurant(restaurant,page);
	}
	public Booking bookingRepositoryfindBySpecialRequirements(String special){
		return bookingRepository.findBySpecialRequirements(special);
	}
	public Booking bookingRepositoryfindById(long id){
		return bookingRepository.findById(id);
	}
	public List<Booking> bookingRepositoryfindByStateAndBookingRestaurant(String accept,Restaurant restaurant){
		return bookingRepository.findByStateAndBookingRestaurant(accept,restaurant);
	}
	//Voucher
	public Page<Voucher> voucherRepositoryfindByRestaurant(Restaurant restaurant,Pageable page){
		return voucherRepository.findByRestaurant(restaurant, page);
	}
	public void voucherRepositorysave(Voucher voucher){
		voucherRepository.save(voucher);
	}
	public Voucher voucherRepositoryfindByName(String name){
		return voucherRepository.findByName(name);
	}
	//User
	public User userRepositoryfindByEmail(String email){
		return userRepository.findByEmail(email);
	}
	public void userRepositorysave(User user){
		userRepository.save(user);
	}
	public List<User> userRepositoryfindByAgeBetween(int min, int max){
		return userRepository.findByAgeBetween(min,max);
	}
	public User userRepositoryfindOne(Long id){
		return userRepository.findOne(id);
	}

	public boolean checkMenu(Menu menu, String email){
		boolean repeated = false;
		List<Menu> menus = restaurantRepository.findByEmail(email).getMenus();
		for (Menu m : menus){
			if (menu.equals(m)){
				repeated = true;
				break;
			}
		}
		return !repeated;
	}
	public void saveMenu(Menu menu, String email){
		menu.setRestaurantMenu(restaurantRepository.findByEmail(email));
		menuRepository.save(menu);
		restaurantRepository.findByEmail(email).getMenus().add(menu);
	}

}
