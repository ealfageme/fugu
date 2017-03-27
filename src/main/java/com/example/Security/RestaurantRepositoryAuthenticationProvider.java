package com.example.Security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import com.example.Entities.*;
import com.example.Repositories.*;
@Component
public class RestaurantRepositoryAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private RestaurantRepository restaurantRepository;
	@Autowired
	private RestaurantComponent restaurantComponent;
	
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		Restaurant restaurant = restaurantRepository.findByEmail(auth.getName());
		if (restaurant == null) {
			System.out.println("Restaurant not found");
			throw new BadCredentialsException("Restaurant not found");
		}
		String password = (String) auth.getCredentials();
		if (!new BCryptPasswordEncoder().matches(password, restaurant.getPassword())) {
			System.out.println("wrong pass");
			throw new BadCredentialsException("Wrong password");
		}

		List<GrantedAuthority> roles = new ArrayList<>();
			roles.add(new SimpleGrantedAuthority(restaurant.getRoles()));
			restaurantComponent.setLoggedRestaurant(restaurant);
		return new UsernamePasswordAuthenticationToken(restaurant.getEmail(), password, roles);
	}

	@Override
	public boolean supports(Class<?> authenticationObject) {
		return true;
	}
}
