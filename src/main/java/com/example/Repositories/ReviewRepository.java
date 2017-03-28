package com.example.Repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Entities.*;
public interface ReviewRepository extends JpaRepository<Review,Long>{
	Page<Review> findByReviewRestaurant(Restaurant restaurant,Pageable page);
}
