package com.example.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Entities.*;
import com.example.Repositories.*;
import com.example.Security.*;
import com.example.Controllers.*;
public interface UserRepository extends JpaRepository<User,Long>{
	User findByName(String name);
	List<User> findByAgeBetween(int min, int max);
	User findByEmail(String email);
}
