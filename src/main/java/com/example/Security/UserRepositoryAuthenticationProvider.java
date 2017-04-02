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
import org.springframework.social.InvalidAuthorizationException;
import org.springframework.social.RevokedAuthorizationException;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.stereotype.Component;

import com.example.Controllers.MainController;
import com.example.Entities.*;
import com.example.Repositories.*;

@Component
public class UserRepositoryAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserComponent userComponent;
	@Autowired
	private MainController controller;
	private Facebook facebook;

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		User user = userRepository.findByEmail(auth.getName());
		User userProfile=new User();
		try{
			facebook = new FacebookTemplate(controller.getAccessToken());
			String [] fields = { "id", "email","name"};
			userProfile = facebook.fetchObject("me", User.class, fields);
			userProfile.setRoles("ROLE_FACEBOOK");
			System.out.println(userProfile.getName());
		}catch(RevokedAuthorizationException e){
			facebook=null;
		}catch(InvalidAuthorizationException ex){
			facebook=null;
		}
		if(facebook!=null){
			userProfile.setEmail("email");
			userProfile.setPassword("password");
			userProfile.setDescription("Facebook description");
			userProfile.setFavouriteFood("Chinese");
			userRepository.save(userProfile);
			List<GrantedAuthority> roles = new ArrayList<>();
			roles.add(new SimpleGrantedAuthority(userProfile.getRoles()));
			userComponent.setLoggedUser(userProfile);
			return new UsernamePasswordAuthenticationToken(userProfile.getEmail(), null, roles);
		}
		if (user == null) {
			System.out.println("user not found");
			throw new BadCredentialsException("User not found");
		}
		String password = (String) auth.getCredentials();
		if (!new BCryptPasswordEncoder().matches(password, user.getPassword())) {
			System.out.println("wrong pass");
			throw new BadCredentialsException("Wrong password");
		}

		List<GrantedAuthority> roles = new ArrayList<>();
			roles.add(new SimpleGrantedAuthority(user.getRoles()));
			userComponent.setLoggedUser(user);
			
		return new UsernamePasswordAuthenticationToken(user.getEmail(), password, roles);
	}

	@Override
	public boolean supports(Class<?> authenticationObject) {
		return true;
	}
}
