package com.example.Controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.PageRequest;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/facebook/", method =  RequestMethod.POST)
public class HelloController {

	private Facebook facebook;
    private ConnectionRepository connectionRepository;
    
    public HelloController(Facebook facebook, ConnectionRepository connectionRepository) {
        this.facebook = facebook;
        this.connectionRepository = connectionRepository;
        
    }

    @GetMapping
    public String helloFacebook(Model model,HttpServletRequest request) {
        if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
        	return "redirect:/connect/facebook";
        }
        System.out.println("ENTRA");
        model.addAttribute("facebookProfile", facebook.userOperations().getUserProfile());
        PagedList<Post> feed = facebook.feedOperations().getFeed();
        model.addAttribute("feed", feed);
        return "main";
    }

}