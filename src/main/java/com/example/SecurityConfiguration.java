package com.example;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	 @Override
	 protected void configure(HttpSecurity http) throws Exception {
	
		 // Public pages
		 http.authorizeRequests().antMatchers("/main/").permitAll();
		 http.authorizeRequests().antMatchers("/public-client/**").permitAll();
		 http.authorizeRequests().antMatchers("/public-restaurant/**").permitAll();
		 http.authorizeRequests().antMatchers("/search-web/").permitAll();
		 http.authorizeRequests().antMatchers("/city/**").permitAll();
		 // Private pages (all other pages)
		 http.authorizeRequests().anyRequest().authenticated();
		 // Login form
		 http.formLogin().loginPage("/main/");
		 http.formLogin().usernameParameter("loginemail");
		 http.formLogin().passwordParameter("loginpassword");
		 http.formLogin().defaultSuccessUrl("/private-client/john");
		 http.formLogin().failureUrl("/public-client/john/");
		 // Logout
		 http.logout().logoutUrl("/logout/");
		 http.logout().logoutSuccessUrl("/main/");
		
		 // Disable CSRF at the moment
		 http.csrf().disable();
		// User
		
	 }
	 
	 @Override
	 protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		 System.out.println("ENTRA");
		 // User
		 auth.inMemoryAuthentication()
		 .withUser("john-snow@website.com").password("yomuero").roles("USER");
	 }

}