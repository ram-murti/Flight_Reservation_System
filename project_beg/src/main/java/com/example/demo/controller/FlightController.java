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
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.dao.FlightDaoInterface;
import com.example.demo.model.Airports;
import com.example.demo.model.Flight;

@Controller
@RequestMapping("/flight")
public class FlightController {
	
    @Autowired
	public FlightDaoInterface dao;
    
    public static List<Airports> airports=new ArrayList<Airports>();
    @RequestMapping("/save")
	public String saveFlight(@ModelAttribute("addflight") Flight flight,Model theModel) {
		String message=dao.saveFlight(flight);
		System.out.println("save flight");
         theModel.addAttribute("message", message);
		return "message";
		
	}
    @RequestMapping("/search")
	public String findByfromAndTo( @ModelAttribute("search") Flight flightt,Model theModel,HttpServletRequest request){
	
		System.out.println(request.getSession().getAttribute("name"));
		System.out.println("github");
    	long userid=(long) request.getSession().getAttribute("name");

	List<Flight> listOfFlight=dao.findByfromAndTo(flightt.from,flightt.to);
			;
	if(listOfFlight != null && !listOfFlight.isEmpty()) {
	theModel.addAttribute("listOfFlight", listOfFlight);
	theModel.addAttribute("userid", userid);

	return "listofFlight";}
	else {
		// TODO: handle exception
		List airport=(List) request.getSession().getAttribute("city_name");	

		theModel.addAttribute("operators",airport);

		theModel.addAttribute("message", "no flight found  ");  
		return "search";
	}
	}
    @RequestMapping("/update")
	public String updateFlight(Flight flight,Model theModel) {
		String message=dao.updateFlight(flight);
		theModel.addAttribute("message", message);
		
		return "message";
		
	}
    @RequestMapping("/delete")
	public String deleteFlight(long id,Model theModel) {
		
		String message=dao.deleteFlight(id);
		theModel.addAttribute("message", message);
		return "message";
		
	}

  
}
 

