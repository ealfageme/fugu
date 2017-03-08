package com.example;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long>{
	User findByName(String name);
	List<User> findByAgeBetween(int min, int max);
}
