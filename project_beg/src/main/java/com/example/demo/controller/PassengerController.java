package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.FlightDaoInterface;
import com.example.demo.dao.PassengerDao;
import com.example.demo.dao.UserDaointerface;
import com.example.demo.model.Flight;
import com.example.demo.model.Passenger;
import com.example.demo.model.Reservation;
import com.example.demo.model.User;

@Controller
@RequestMapping("/pass")

public class PassengerController {
    @Autowired
	public PassengerDao passengerDao;

	@Autowired
	public FlightDaoInterface dao;
	@Autowired
	public UserDaointerface userdao;
	
	@RequestMapping("/save")
	public String savePassenger(@ModelAttribute("passenger") Passenger pass,Model theModel,@RequestParam("fid") String sid,@RequestParam("userid") String usid,HttpServletRequest request) {
		try {
			
			long id=Integer.parseInt(sid);
			  Optional<Flight> fi=dao.findById(id);
			  Flight flight=fi.get();
			long fid=flight.getId();
			long userid=(long) request.getSession().getAttribute("name");
            Optional<User> user=userdao.findById(userid);
            
            User use=user.get();
         
		
			pass.setUser(use);
			String message=passengerDao.savePassenger(pass);
			System.out.println(pass.getId());
			long pid=pass.getId();
			theModel.addAttribute("passenger",pass);
			theModel.addAttribute("fid", fid);
			theModel.addAttribute("flight", flight);
			theModel.addAttribute("pid", pid);

			theModel.addAttribute("userid", userid);
			theModel.addAttribute("reservation", new Reservation());

          return "reservation";
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return "message";

		}
		
	}
    
	public String findById(long id,Model theModel ){
		 try {
		Optional<Passenger>	pass=passengerDao.findById(id);
		theModel.addAttribute("pass", pass);
		theModel. addAttribute("message", "");
		
		} catch (Exception e) {
			// TODO: handle exception
			theModel. addAttribute("message", "");

		}
		
	    return "";	
	}
    @RequestMapping()
	public String updatePassenger(@ModelAttribute("passenger") Passenger passenger , Model theModel ) {
    	String message=passengerDao.updatePassenger(passenger);
    	theModel.addAttribute("message", message);
		return null;
		
	}
    @RequestMapping("/user")
	public String findAllAddedPassengers(@RequestParam("fid") String sid,Model theModel,HttpServletRequest request) {
		System.out.println("here");
    	long userid=(long) request.getSession().getAttribute("name");
        Optional<User> user=userdao.findById(userid);
        
        User use=user.get();
        long fid=Integer.parseInt(sid);
		 System.out.println("error 404");
		List<Passenger> allPassengers=passengerDao.findByUser(use);
		 System.out.println("error 404");
		request.getSession().setAttribute("fname", fid);
		theModel.addAttribute("allPassengers", allPassengers);
		theModel.addAttribute("passenger", new Passenger());
		theModel.addAttribute("userid", userid);
		theModel.addAttribute("fid", fid);



		return "prevAddedPass";
		
	}
}
