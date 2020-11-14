package com.example.demo.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.dao.FlightDaoInterface;
import com.example.demo.dao.PassengerDao;
import com.example.demo.dao.UserDaointerface;
import com.example.demo.model.Flight;
import com.example.demo.model.Passenger;
import com.example.demo.model.Reservation;
import com.example.demo.model.User;

@Controller
@RequestMapping("/")

public class HomeController {

	@Autowired
	public FlightDaoInterface dao;
	@Autowired
	public UserDaointerface userdao;
	@Autowired
	 public PassengerDao pdao;
	
	
	
@RequestMapping("/user")
public String tSignup(Model themodel) {

	System.out.println("Home controller");
	themodel.addAttribute("signUp", new User());
	return "userSignup";
}
@RequestMapping("/login")
public String tLogin(Model themodel) {
	System.out.println("Home controller");
	themodel.addAttribute("login", new User());
	return "login";
}
@RequestMapping("/addFlight")
public String addFlight(@ModelAttribute Flight flight,Model theModel) {
		System.out.println("Flight Home Controller");
		theModel.addAttribute("addflight", new Flight());

	return "addFlight";
	
}

@RequestMapping("/addPassenger")
public String addPassenger(Model theModel,@RequestParam("fid") String fli,@RequestParam("userid") String userid ) {
	System.out.println("add Passenger");
	System.out.println(fli+userid);
  theModel.addAttribute("fid", fli);
  theModel.addAttribute("userid", userid);

	theModel.addAttribute("pass", new Passenger());

   
	return "passenger";
}
@RequestMapping("/reservationdi")
public String rservationPage(Model theModel,@RequestParam("pid") String pid ,HttpServletRequest request) {
	
	long userid=(long) request.getSession().getAttribute("name");
    Optional<User> user=userdao.findById(userid);
    
    User use=user.get();
    long fid=(long) request.getSession().getAttribute("fname");
    Optional<Flight> fi=dao.findById(fid);
	  Flight flight=fi.get();
  theModel.addAttribute("userid", userid);
   theModel.addAttribute("reservation", new Reservation());
   theModel.addAttribute("flight", flight);
   theModel.addAttribute("fid", fid);

	 long passid=Integer.parseInt(pid);
	 Optional<Passenger>  pass=pdao.findById(passid);
	 Passenger finalPass=pass.get();
	 theModel.addAttribute("passenger", finalPass);
	   theModel.addAttribute("pid", finalPass.getId());

	return "reservation";
}
@RequestMapping("/getAllTickets")
public String getAllTickets() {

	System.out.println("Tickets");
	return "listOfRes";
}
@RequestMapping(value = "/sendemail")
public String sendEmail(Model theModel) throws AddressException, MessagingException, IOException {
	theModel.addAttribute("message", "mail sent succesfully");
   return "message";
}  

}
