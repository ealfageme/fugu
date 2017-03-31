package com.example.Services;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.Repositories.UserRepository;
import com.example.Entities.User;

public class ClientService {
	@Autowired 
	private UserRepository clientRepository;
	
	public User clientServiceFindOne(Long id){
		return clientRepository.findOne(id);
	}
	public User clientServiceFindByName(String name){
		return clientRepository.findByName(name);
	}
	public void clientServiceSave(User user){
		clientRepository.save(user);
	}
	public User clientServiceFindByEmail(String email){
		return clientRepository.findByEmail(email);
	}
	public void clientServiceDelete(User user){
		clientRepository.delete(user);
	}
}
