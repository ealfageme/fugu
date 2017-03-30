package com.example.Repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Entities.*;
public interface MenuRepository extends JpaRepository<Menu,Long>{
	Page<Menu> findByRestaurantMenu(Restaurant restaurant,Pageable page);
	Menu findByDish(String dish);
}
