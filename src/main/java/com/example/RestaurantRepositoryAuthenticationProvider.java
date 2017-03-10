package com.example;

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

@Component
public class RestaurantRepositoryAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {

		Restaurant restaurant = restaurantRepository.findByName(auth.getName());

		if (restaurant == null) {
			throw new BadCredentialsException("User not found");
		}

		String password = (String) auth.getCredentials();
		if (!new BCryptPasswordEncoder().matches(password, restaurant.getPassword())) {
			throw new BadCredentialsException("Wrong password");
		}

		List<GrantedAuthority> roles = new ArrayList<>();
			roles.add(new SimpleGrantedAuthority(restaurant.getRoles()));

		return new UsernamePasswordAuthenticationToken(restaurant.getName(), password, roles);
	}

	@Override
	public boolean supports(Class<?> authenticationObject) {
		return true;
	}
}
