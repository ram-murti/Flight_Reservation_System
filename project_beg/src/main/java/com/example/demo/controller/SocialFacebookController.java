package com.example.demo.controller;

import org.springframework.social.connect.Connection;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SocialFacebookController {

	private FacebookConnectionFactory factory = new FacebookConnectionFactory("1126463077736973",
			"4cf52d67fdaa2040fc729e46713d40af");

	
	@GetMapping(value = "/facebook/useApplication")
	public String producer() {

		OAuth2Operations operations = factory.getOAuthOperations();
		OAuth2Parameters params = new OAuth2Parameters();

		params.setRedirectUri("http://localhost:1234/forwardLogin");
		params.setScope("email,public_profile");

		String url = operations.buildAuthenticateUrl(params);
		System.out.println("The URL is" + url);
		return "redirect:" + url;

	}

	@RequestMapping(value = "/forwardLogin")
	public String prodducer(@RequestParam("code") String authorizationCode) {
		OAuth2Operations operations = factory.getOAuthOperations();
		AccessGrant accessToken = operations.exchangeForAccess(authorizationCode, "http://localhost:1234/forwardLogin",
				null);

		Connection<Facebook> connection = factory.createConnection(accessToken);
		Facebook facebook = connection.getApi();
		String[] fields = { "id", "email", "first_name", "last_name" };
		User userProfile = facebook.fetchObject("me", User.class, fields);
		System.out.println(userProfile.getFirstName()+userProfile.getLastName()+userProfile.getId()+userProfile.getEmail());
		String url="http://localhost:1234/api/save/facebook?fname="+userProfile.getFirstName()+"&&email="+userProfile.getEmail()+"&&lname="+userProfile.getLastName();
		
		return "redirect:"+url;
		

	}

}