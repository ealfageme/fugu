package com.example.Controllers;

import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class DefaultController {
	
    @RequestMapping("/default/")
    public String defaultAfterLogin(HttpServletRequest request,Authentication authentication) {
    	System.out.println("entro");
    		if (request.isUserInRole("USER")) {
    			System.out.println("entro2");
                return "redirect:/private-client/";}
    		if (request.isUserInRole("RESTAURANT")) {
    			System.out.println("entro2");
                    return "redirect:/private-restaurant/";}
    	return "redirect:/main/";
    }
}