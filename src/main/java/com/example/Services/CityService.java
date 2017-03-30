package com.example.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entities.City;
import com.example.Repositories.CityRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository cityRepository;
	
	
	public List<City> cityServiceFindAll(){
		return cityRepository.findAll();
	}
	public City cityServiceFindByName(String name){
		return cityRepository.findByName(name);
		
	}
}
