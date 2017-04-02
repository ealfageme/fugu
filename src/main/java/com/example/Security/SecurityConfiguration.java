package com.example.Security;

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
	 	public RestaurantRepositoryAuthenticationProvider authenticationProviderRestaurant;
	 
	 @Override
	 protected void configure(HttpSecurity http) throws Exception {
	
		 // Public pages
		 http.authorizeRequests().antMatchers("/main/").permitAll();
		 http.authorizeRequests().antMatchers("/public-client/**").permitAll();
		 http.authorizeRequests().antMatchers("/public-restaurant/**").permitAll();
		 http.authorizeRequests().antMatchers("/search-web/").permitAll();
		 http.authorizeRequests().antMatchers("/city/**").permitAll();
		 http.authorizeRequests().antMatchers("/clients/").permitAll();
		 
		 // Private pages (all other pages)
		 http.authorizeRequests().antMatchers("/private-client/").hasAnyRole("USER","FACEBOOK");
		 http.authorizeRequests().antMatchers("/private-restaurant/").hasAnyRole("RESTAURANT");
		 
		 // Login form
		 http.formLogin().loginPage("/main2/");
		 http.formLogin().usernameParameter("loginemail");
		 http.formLogin().passwordParameter("loginpassword");
		 http.formLogin().defaultSuccessUrl("/main/");
		 http.formLogin().failureUrl("/main2/");
		 // Logout
		 http.logout().logoutSuccessUrl("/main/");
		 http.csrf().disable();

		
	 }
	 
	 @Override
	 protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	     
	     auth.authenticationProvider(authenticationProviderRestaurant);
	     auth.authenticationProvider(authenticationProviderUser);

	 }

}