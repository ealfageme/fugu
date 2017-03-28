package com.example.Repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Entities.*;
public interface BookingRepository extends JpaRepository<Booking,Long>{
	List<Booking> findByStateAndBookingRestaurant(String state,Restaurant restaurant);
	List<Booking> findByStateAndBookingUser(String state,User user);
	Booking findById(long id);
	Page<Booking>findByBookingRestaurant(Restaurant restaurant, Pageable page);
}
