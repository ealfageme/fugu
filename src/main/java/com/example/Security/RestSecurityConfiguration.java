package com.example.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class RestSecurityConfiguration extends WebSecurityConfigurerAdapter {
	 @Autowired
	    public UserRepositoryAuthenticationProvider authenticationProviderUser;
	 @Autowired
	 	public RestaurantRepositoryAuthenticationProvider authenticationProviderRestaurant;
	 
	 @Override
	 protected void configure(HttpSecurity http) throws Exception {
	
		 // Public pages
		 http.authorizeRequests().antMatchers("/api/main/").permitAll();
		 http.authorizeRequests().antMatchers("/api/public-client/**").permitAll();
		 http.authorizeRequests().antMatchers("/api/public-restaurant/**").permitAll();
		 http.authorizeRequests().antMatchers("/api/search-web/").permitAll();
		 http.authorizeRequests().antMatchers("/api/city/**").permitAll();
		 http.authorizeRequests().antMatchers("/api/clients/").permitAll();
		 
		 // Private pages (all other pages)
		 http.authorizeRequests().antMatchers("/api/private-client/").hasAnyRole("USER");
		 http.authorizeRequests().antMatchers("/api/private-restaurant/").hasAnyRole("RESTAURANT");
		 
		 // Login form
		 http.formLogin().loginPage("/api/main2/");
		 http.formLogin().usernameParameter("loginemail");
		 http.formLogin().passwordParameter("loginpassword");
		 http.formLogin().defaultSuccessUrl("/api/main/");
		 http.formLogin().failureUrl("/api/main2/");
		 // Logout
		 http.logout().logoutSuccessUrl("/api/main/");
		

		
	 }
	 
	 @Override
	 protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	     
	     auth.authenticationProvider(authenticationProviderRestaurant);
	     auth.authenticationProvider(authenticationProviderUser);

	 }

}