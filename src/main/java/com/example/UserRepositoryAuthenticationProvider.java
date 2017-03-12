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
public class UserRepositoryAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserRepository userRepository;

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		System.out.println(auth.getName());
		User user = userRepository.findByEmail(auth.getName());

		if (user == null) {
			System.out.println("user not found");
			throw new BadCredentialsException("User not found");
		}
		System.out.println(auth.getCredentials());
		String password = (String) auth.getCredentials();
		if (!new BCryptPasswordEncoder().matches(password, user.getPassword())) {
			System.out.println("wrong pass");
			throw new BadCredentialsException("Wrong password");
		}

		List<GrantedAuthority> roles = new ArrayList<>();
			roles.add(new SimpleGrantedAuthority(user.getRoles()));
			System.out.println(roles.get(0));
		return new UsernamePasswordAuthenticationToken(user.getEmail(), password, roles);
	}

	@Override
	public boolean supports(Class<?> authenticationObject) {
		return true;
	}
}
