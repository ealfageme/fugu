package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class DataBaseUsage implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	private RestaurantRepository restaurantRepository;
	private ReviewRepository reviewRepository;
	private VoucherRepository voucherRepository;
	private BookingRepository bookingRepository;
	
	@Override
	public void run (String... args) throws Exception{
		
		
	}
}
