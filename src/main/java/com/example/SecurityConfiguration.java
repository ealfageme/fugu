package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    public UserRepositoryAuthenticationProvider authenticationProvider;
    @Autowired
    public UserRepository userRepository;
    @Autowired
    public RestaurantRepository restaurantRepository;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	// Public pages
        http.authorizeRequests().antMatchers("/main").permitAll();
        http.authorizeRequests().antMatchers("/public-client/**").permitAll();
        http.authorizeRequests().antMatchers("/public-restaurant/**").permitAll();
        http.authorizeRequests().antMatchers("/search-web").permitAll();

        // Private pages (all other pages)
        /*for(Restaurant restaurant : restaurantRepository.findAll()){
        	http.authorizeRequests().antMatchers("/private-restaurant/"+restaurant.getName()).hasAnyRole("RESTAURANT"+restaurant.getName());
        }
        for(User user : userRepository.findAll()){
        	 http.authorizeRequests().antMatchers("/private-client/"+user.getName()).hasAnyRole("USER"+user.getName());
        }*/
        http.authorizeRequests().antMatchers("/private-client/jhon").hasAnyRole("USER");
        

        // Login form
        http.formLogin().loginPage("/main");
        http.formLogin().usernameParameter("email");
        http.formLogin().passwordParameter("password");
        http.formLogin().defaultSuccessUrl("/private-client/"+"john");
        http.formLogin().failureUrl("/error");
        // Logout
        http.logout().logoutUrl("/main");
        http.logout().logoutSuccessUrl("/");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        // Database authentication provider
        auth.authenticationProvider(authenticationProvider);
    }
}
