package com.example.demo.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.dao.UserDaointerface;
import com.example.demo.model.Flight;
import com.example.demo.model.User;

@Controller
@RequestMapping("/api")
public class SignUpController {
    @Autowired
	public UserDaointerface dao;
    
   

    

    @RequestMapping("/user")
    public String tSignup(Model themodel) {

    	System.out.println("Home controller");
    	themodel.addAttribute("signUp", new User());
    	return "userSignup";
    }
   @RequestMapping("/findByunameandpass")
	public String findByUsernamePassword(Model theModel,@ModelAttribute("login") User user1,HttpServletRequest request,String a) {
           
    	try{
    		System.out.println(user1.username+""+ user1.getPassword());
    		User user=dao.findByUsernamePassword(user1.username, user1.password);
    		System.out.println(user1.username);
           long userid=user.getId();
   		System.out.println(user1.username);
   		List airport=null;
   		 airport=getGithubContentUsingURLConnection();
        request.getSession().setAttribute("name", userid);
        request.getSession().setAttribute("city_name", airport);

    	theModel.addAttribute("search", new Flight());
    	theModel.addAttribute("userid", userid);
    	theModel.addAttribute("operators",airport);
    	
    	airport=null;

			return "search";
		}
		catch(Exception e) {
			theModel.addAttribute("message", "Invalid credential");
			return "login";
		}
    
    }
    @RequestMapping("/save")
    public String saveUser(@ModelAttribute("signUp") User user, Model themodel) {
    	System.out.println("username:"+user.getUsername());
    	
    	String message=dao.saveUser(user);
    System.out.println("save->controller->"+message);
    themodel.addAttribute("message", message);
    	return "message" ;
    }
 
    @PutMapping("/update")
    public String updateUser(User user) {
    	String message=dao.saveUser(user);
       
    	
		return "";
    	
    }
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {    	
    	String message=dao.deleteUser(id);
		return "userSignup";
    	
    }
    private static List getGithubContentUsingURLConnection() {
        ArrayList<String> airport = new ArrayList<String>();

    	String token="1bd345e9c0fee3df9df7f13f48f21607ac952d5d";
    	String url="raw.githubusercontent.com/ram-murti/ibm-fsd000GER/master/airport.txt";
		String newUrl = "https://" + url;
		System.out.println(newUrl);
		try {
			URL myURL = new URL(newUrl);
			URLConnection connection = myURL.openConnection();
			token = token + ":x-oauth-basic";
			String authString = "Basic " + Base64.encodeBase64String(token.getBytes());
			connection.setRequestProperty("Authorization", authString);
			InputStream crunchifyInStream = connection.getInputStream();
			 InputStreamReader isReader = new InputStreamReader(crunchifyInStream);
		      //Creating a BufferedReader object
		      BufferedReader reader = new BufferedReader(isReader);
		      StringBuffer sb = new StringBuffer();
		      String str=null;
		      while((str = reader.readLine())!= null){
		         sb.append(str);
		      }
		      System.out.println(sb.toString());
		      
			JSONObject obj = new JSONObject(sb.toString());
			sb=null;
			JSONArray arr = obj.getJSONArray("airports");
			 
			System.out.println(arr);
            String post_id = arr.getJSONObject(0).getString("IATA_code");
            for (int i = 0; i < arr.length(); i++) {
                String airport1 = arr.getJSONObject(i).getString("city_name");
                String airport_name=arr.getJSONObject(i).getString("airport_name");
                String totaldetail=airport1 +"->"+airport_name;
                airport.add(totaldetail);
            }
            return airport;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
    @RequestMapping("/save/facebook")
    public String saveUserViaFacebook(User user,Model theModel,HttpServletRequest request,@RequestParam("fname") String fname,@RequestParam("lname") String lname,@RequestParam("email") String email) {
     try {   User user1=dao.findByUsernamePassword(fname+lname, "xxx");
  
	 long userid=user1.getId();
  List airport=null;
		 airport=getGithubContentUsingURLConnection();
 request.getSession().setAttribute("name", userid);
 request.getSession().setAttribute("city_name", airport);

	theModel.addAttribute("search", new Flight());
	theModel.addAttribute("userid", userid);
	theModel.addAttribute("operators",airport);
	
	airport=null;
  	return "search" ;  
  }
  catch(Exception e) {
	  user.setUsername(fname+lname);
	  	user.setPassword("xxx");
	  	String message=dao.saveUser(user);
	  System.out.println("save->controller->"+message);
		 User user1=dao.findByUsernamePassword(fname+lname, "xxx");
	 long userid=user1.getId();
    List airport=null;
		 airport=getGithubContentUsingURLConnection();
   request.getSession().setAttribute("name", userid);
   request.getSession().setAttribute("city_name", airport);

	theModel.addAttribute("search", new Flight());
	theModel.addAttribute("userid", userid);
	theModel.addAttribute("operators",airport);
	
	airport=null;
    	return "search" ;
    }}
}
