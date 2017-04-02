package com.example.Controllers;

import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class DefaultController {
	
    @RequestMapping("/default/")
    public String defaultAfterLogin(HttpServletRequest request,Authentication authentication) {
		if (request.isUserInRole("USER")) {
            return "redirect:/private-client/";
        }
		if (request.isUserInRole("RESTAURANT")) {
            return "redirect:/private-restaurant/";
        }
		if (request.isUserInRole("FACEBOOK")) {
            return "redirect:/private-client/";
        }

    	return "redirect:/main/";
    }
}