package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	 @Autowired
	    public UserRepositoryAuthenticationProvider authenticationProviderUser;
	 @Autowired
	    public UserRepository users;
	 @Autowired
	    public RestaurantRepository restaurants;

	 @Autowired
	 public RestaurantRepositoryAuthenticationProvider authenticationProviderRestaurant;
	 @Override
	 protected void configure(HttpSecurity http) throws Exception {
	
		 // Public pages
		 http.authorizeRequests().antMatchers("/main/").permitAll();
		 http.authorizeRequests().antMatchers("/public-client/**").permitAll();
		 http.authorizeRequests().antMatchers("/public-restaurant/**").permitAll();
		 http.authorizeRequests().antMatchers("/search-web/").permitAll();
		 http.authorizeRequests().antMatchers("/city/**").permitAll();
		 // Private pages (all other pages)
		 //http.authorizeRequests().anyRequest().authenticated();
		 for(User user : users.findAll()){
			 System.out.println(user.getName());
			 http.authorizeRequests().antMatchers("/private-client/"+user.getName()).hasAnyRole("USER"+user.getName());
		 }
		 
		 for(Restaurant restaurant : restaurants.findAll()){
			 System.out.println(restaurant.getName());
			 http.authorizeRequests().antMatchers("/private-restaurant/"+restaurant.getName()).hasAnyRole("RESTAURANT"+restaurant.getName());
		 }
		 
		 //http.authorizeRequests().antMatchers("/private-client/john-lennon").hasAnyRole("USER"+"john-lennon");
		 //http.authorizeRequests().antMatchers("/private-restaurant/*").hasAnyRole("RESTAURANT");
		 // Login form
		 http.formLogin().loginPage("/main/");
		 http.formLogin().usernameParameter("loginemail");
		 http.formLogin().passwordParameter("loginpassword");
		 http.formLogin().defaultSuccessUrl("/default/");
		 http.formLogin().failureUrl("/main/");
		 // Logout
		 http.logout().logoutSuccessUrl("/main/");
		
		 // Disable CSRF at the moment
		 //http.csrf().disable();
		// User
		
	 }
	 
	 @Override
	 protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		 // User
		 // Database authentication provider
	     
	     auth.authenticationProvider(authenticationProviderRestaurant);
	     auth.authenticationProvider(authenticationProviderUser);

	 }

}