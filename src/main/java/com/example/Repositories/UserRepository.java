package com.example.Repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Entities.*;
public interface UserRepository extends JpaRepository<User,Long>{
	User findById(long id);
	User findByName(String name);
	List<User> findByAgeBetween(int min, int max);
	User findByEmail(String email);
	Page<User> findAll(Pageable pageable);
}
