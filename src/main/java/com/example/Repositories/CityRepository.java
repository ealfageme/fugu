package com.example.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Entities.*;
public interface CityRepository extends JpaRepository<City,Long>{
	
	City findByName(String name);
}
