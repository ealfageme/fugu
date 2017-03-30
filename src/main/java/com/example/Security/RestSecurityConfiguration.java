package com.example.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(1)
public class RestSecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	public UserRepositoryAuthenticationProvider authenticationProviderUser;
	@Autowired
	public RestaurantRepositoryAuthenticationProvider authenticationProviderRestaurant;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.antMatcher("/api/**");
		// Public pages
		http.authorizeRequests().antMatchers("/api/main/").permitAll();
		http.authorizeRequests().antMatchers("/api/search-web/").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/city/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/clients/signin").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/restaurants/signin").permitAll();

		// Private pages (all other pages)
		http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/clients/**").hasAnyRole("USER");
		http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/restaurants/**/book").hasAnyRole("USER");
		http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/restaurants/**/reviews").hasAnyRole("USER");
		http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/restaurants/").hasAnyRole("RESTAURANT");
		http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/clients/**").hasAnyRole("USER");
		http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/restaurants/**").hasAnyRole("RESTAURANT");

		// Use Http Basic Authentication
		http.httpBasic();
		
		// Do not redirect when logout
		http.logout().logoutSuccessHandler((rq, rs, a) -> {
		});
		http.csrf().disable();

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.authenticationProvider(authenticationProviderRestaurant);
		auth.authenticationProvider(authenticationProviderUser);

	}

}