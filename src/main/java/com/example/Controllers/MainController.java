package com.example.Controllers;


import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.social.InvalidAuthorizationException;
import org.springframework.social.RevokedAuthorizationException;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import com.example.Entities.*;
import com.example.Services.CityService;
import com.example.Services.ClientService;
import com.example.Services.RestaurantService;

@Controller
public class MainController {

	@Autowired
	private RestaurantService restaurantService;
	@Autowired
	private CityService cityService;
	@Autowired
	private ClientService clientService;
	

	private Facebook facebook;
	private String accessToken;

	@RequestMapping(value="/main/", method = { RequestMethod.GET, RequestMethod.POST })
	public String main(Model model, HttpServletRequest request, Authentication authentication, @RequestParam(required=false) String restaurantname, 
			 @RequestParam(required = false) String restaurantaddress, @RequestParam(required=false) String kindoffood,
			 @RequestParam(required = false) String restaurantcity, @RequestParam(required=false) String restaurantemail,
			 @RequestParam(required = false) String restaurantphone, @RequestParam(required=false) String restaurantdescription,
			 @RequestParam(required = false) String restaurantpassword, @RequestParam(required=false) String username,
			 @RequestParam(required = false) String useremail, @RequestParam(required=false) String userage,
			 @RequestParam(required = false) String favouritefood, @RequestParam(required=false) String userdescription,
			 @RequestParam(required = false) String userpassword ,@RequestParam(required=false) String oauth) {
		model.addAttribute("restaurant", restaurantService.restaurantRepositoryfindByRateBetweenOrderByRateDesc(new Double(0.0), new Double(5.0), new PageRequest(0, 4)));
		if (restaurantname!=null){
			Restaurant rest= new Restaurant (restaurantname,restaurantaddress,restaurantdescription,restaurantemail,kindoffood,Integer.parseInt(restaurantphone), 0, 0,restaurantpassword,true,true,true);
			rest.setCity(cityService.cityServiceFindByName(restaurantcity));
			restaurantService.restaurantRepositorySave(rest);
		}
		if (username!=null){
			User user = new User(username,useremail,userdescription, userpassword ,Integer.parseInt(userage),favouritefood);
			clientService.userRepositorysave(user);
		}
		
		User userProfile=new User();
		try{
			facebook = new FacebookTemplate(accessToken);
			String [] fields = { "id", "email", "name"};
			userProfile = facebook.fetchObject("me", User.class, fields);
		}catch(RevokedAuthorizationException e){
			facebook=null;
		}catch(InvalidAuthorizationException ex){
			facebook=null;
		}
		
		model.addAttribute("inSession", (request.isUserInRole("USER")||request.isUserInRole("RESTAURANT")||facebook!=null))
			.addAttribute("outSession", !request.isUserInRole("USER")&&!request.isUserInRole("RESTAURANT")&&facebook==null)
			.addAttribute("inNormalSession", (request.isUserInRole("USER")||request.isUserInRole("RESTAURANT")))
			.addAttribute("inFacebookSession", facebook!=null);

		if(request.isUserInRole("USER")){
			model.addAttribute("feedbackname", clientService.userRepositoryFindByEmail(authentication.getName()).getName())
				.addAttribute("feedbackemail", authentication.getName());
		}else if(request.isUserInRole("RESTAURANT")){
			model.addAttribute("feedbackname", restaurantService.restaurantRepositoryFindByEmail(authentication.getName()).getName())
				.addAttribute("feedbackemail", authentication.getName());
		}else if(facebook!=null){
			model.addAttribute("feedbackname", userProfile.getName())
				.addAttribute("feedbackemail", "");
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
		User user=new User();
		try{
			facebook = new FacebookTemplate(accessToken);
			String [] fields = { "id", "email", "name"};
			user = facebook.fetchObject("me", User.class, fields);
		}catch(RevokedAuthorizationException e){
			facebook=null;
		}catch(InvalidAuthorizationException ex){
			facebook=null;
		}
		this.accessToken=null;
		if(facebook != null)
			clientService.userRepositoryDeleteUser(clientService.userRepositoryFindByName(user.getName()));
		return "redirect:/main/";
	}
	
	@RequestMapping("/facebooklogin/{token}")
	public String facebooklogin(Model model, @PathVariable String token) {
		this.accessToken=token;
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