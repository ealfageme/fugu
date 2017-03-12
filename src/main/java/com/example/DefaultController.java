package com.example;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class DefaultController {
	@Autowired
	private UserRepository userRepository;
	
    @RequestMapping("/default/")
    public String defaultAfterLogin(HttpServletRequest request,Authentication authentication) {
        if (request.isUserInRole("USER")) {
        	User user=userRepository.findByEmail(authentication.getName());
            return "redirect:/private-client/"+user.getName();
        }
        return "redirect:/private-restaurant/American%20Whey/";
    }
}