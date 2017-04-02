package com.example.Controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.social.InvalidAuthorizationException;
import org.springframework.social.RevokedAuthorizationException;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.social.facebook.api.User;



import com.example.Entities.*;
import com.example.Repositories.*;
import com.example.Security.UserComponent;

@Controller
public class MainController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RestaurantRepository restaurantRepository;
	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private UserComponent userComponent;
	

	private Facebook facebook;
	private String accessToken;

	@RequestMapping(value="/main/", method = { RequestMethod.GET, RequestMethod.POST })
	public String main(Model model, HttpServletRequest request, Authentication authentication, @RequestParam(required=false) String restaurantname, 
			 @RequestParam(required=false) String restaurantaddress,@RequestParam(required=false) String kindoffood,
			 @RequestParam(required=false) String restaurantcity,@RequestParam(required=false) String restaurantemail,
			 @RequestParam(required=false) String restaurantphone,@RequestParam(required=false) String restaurantdescription,
			 @RequestParam(required=false) String restaurantpassword,@RequestParam(required=false) String username,
			@RequestParam(required=false) String useremail,@RequestParam(required=false) String userage,
			@RequestParam(required=false) String favouritefood,@RequestParam(required=false) String userdescription,
			@RequestParam(required=false) String userpassword,@RequestParam(required=false) String oauth) {
		model.addAttribute("restaurant", restaurantRepository.findByRateBetweenOrderByRateDesc(new Double(0.0), new Double(5.0), new PageRequest(0, 4)));
		if (restaurantname!=null){
			Restaurant rest= new Restaurant (restaurantname,restaurantaddress,restaurantdescription,restaurantemail,kindoffood,Integer.parseInt(restaurantphone), 0, 0,restaurantpassword,true,true,true);
			rest.setCity(cityRepository.findByName(restaurantcity));
			restaurantRepository.save(rest);
		}
		if (username!=null){
			User user = new User(username,useremail,userdescription, userpassword ,Integer.parseInt(userage),favouritefood);
			userRepository.save(user);
		}
		User userProfile=new User();
		try{
			facebook = new FacebookTemplate(accessToken);
			String [] fields = { "id", "email","name"};
			userProfile = facebook.fetchObject("me", User.class, fields);
		}catch(RevokedAuthorizationException e){
			facebook=null;
		}catch(InvalidAuthorizationException ex){
			facebook=null;
		}
		model.addAttribute("inSession", (request.isUserInRole("USER")||request.isUserInRole("RESTAURANT")||facebook!=null));
		model.addAttribute("outSession", !request.isUserInRole("USER")&&!request.isUserInRole("RESTAURANT")&&facebook==null);
		model.addAttribute("inNormalSession", (request.isUserInRole("USER")||request.isUserInRole("RESTAURANT")));
		model.addAttribute("inFacebookSession", facebook!=null);

		if(request.isUserInRole("USER")){
			model.addAttribute("feedbackname", userRepository.findByEmail(authentication.getName()).getName());
			model.addAttribute("feedbackemail", authentication.getName());
		}else if(request.isUserInRole("RESTAURANT")){
			model.addAttribute("feedbackname", restaurantRepository.findByEmail(authentication.getName()).getName());
			model.addAttribute("feedbackemail", authentication.getName());
		}else if(facebook!=null){
			model.addAttribute("feedbackname", userProfile.getName());
			model.addAttribute("feedbackemail", "");
		}
		return "main";
	}
	
	@RequestMapping(value="/main2/")
	public String loginfailure(Model model){
		return "redirect:/main/";
	}

	
	@RequestMapping("/error/")
	public String error(Model model) {
		return "error";
	}
	
	@RequestMapping("/main/logout")
	public String facebooklogout(Model model,Authentication auth) {
		this.accessToken=null;
		User user = userRepository.findByEmail(auth.getName());
		userRepository.delete(user);
		return "redirect:/main/";
	}
	
	@RequestMapping("/facebooklogin/{token}")
	public String facebooklogin(Model model, @PathVariable String token) {
		this.accessToken=token;
		User userProfile=new User();
		try{
			facebook = new FacebookTemplate(accessToken);
			String [] fields = { "id", "email","name"};
			userProfile = facebook.fetchObject("me", User.class, fields);userProfile.setRoles("USER");
			List<GrantedAuthority> roles = new ArrayList<>();
			userProfile.setRoles("ROLE_USER");
			roles.add(new SimpleGrantedAuthority(userProfile.getRoles()));
			userComponent.setLoggedUser(userProfile);
		}catch(RevokedAuthorizationException e){
			facebook=null;
		}catch(InvalidAuthorizationException ex){
			facebook=null;
		}
		model.addAttribute("loginemail", "email");
		model.addAttribute("loginpassword", "password");
		return "facebooklogin";
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	
}