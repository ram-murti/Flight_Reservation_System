package com.example.demo.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.FlightDaoInterface;
import com.example.demo.dao.PassengerDao;
import com.example.demo.dao.ReservationDao;
import com.example.demo.dao.UserDaointerface;
import com.example.demo.model.Flight;
import com.example.demo.model.Passenger;
import com.example.demo.model.Reservation;
import com.example.demo.model.User;

@Controller
@RequestMapping("/ticket")
public class ReservationController {
	
 @Autowired	
 public ReservationDao dao;
 @Autowired
 public UserDaointerface udao;
 @Autowired
 public PassengerDao pdao;
 @Autowired
 public FlightDaoInterface fdao;

 
 @RequestMapping("/save")
 public String saveReservation(@RequestParam("pid") String pid,Model theModel,HttpServletRequest request) {
	 
	 
 try {
	 long id=(long) request.getSession().getAttribute("fname");
	  Optional<Flight> fi=fdao.findById(id);
	  Flight flight=fi.get();
	long fid=flight.getId();
	long userid=(long) request.getSession().getAttribute("name");
   Optional<User> user=udao.findById(userid);
   
   User use=user.get();

	 long passid=Integer.parseInt(pid);
	 Optional<Passenger>  pass=pdao.findById(passid);
	 Passenger finalPass=pass.get();

	 Reservation res=new Reservation(flight,use,finalPass);
	 
	String message=dao.saveReservation(res);
	sendmail(res);
	theModel.addAttribute("message","mail has been sent");
	return "message";
} catch (Exception e) {
	// TODO: handle exception
	 theModel.addAttribute("message", "something went wrong ");
	 return "message";
	
}	 
	 
 }
 @RequestMapping("/getAllReservations")
public String getAllReservations(HttpServletRequest request,Model theModel) {
	 
		long userid=(long) request.getSession().getAttribute("name");
		System.out.println("Reservation");
		System.out.println(userid);
		   Optional<User> user=udao.findById(userid);
		   
		   User use=user.get();
		   List<Reservation> totalres=dao.findByUsers(use);
			if(totalres != null && !totalres.isEmpty()) {
				System.out.println("here");
				theModel.addAttribute("totalres",totalres);
				
				return "listOfRes";
			}
			else {
				// TODO: handle exception
				theModel.addAttribute("message", "no ticket booked from this user account");  
				return "message";
			}
	
}
 private void sendmail(Reservation res) throws AddressException, MessagingException, IOException {
	   Properties props = new Properties();
	   props.put("mail.smtp.auth", "true");
	   props.put("mail.smtp.starttls.enable", "true");
	   props.put("mail.smtp.host", "smtp.gmail.com");
	   props.put("mail.smtp.port", "587");
	   
	   Session session = Session.getInstance(props, new javax.mail.Authenticator() {
	      protected PasswordAuthentication getPasswordAuthentication() {
	         return new PasswordAuthentication("rammurti638@gmail.com", "9264247681");
	      }
	   });
	   Message msg = new MimeMessage(session);
	   msg.setFrom(new InternetAddress("rammurti638@gmail.com", false));

	   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(res.user.getEmail()));
	   System.out.println(res.user.getEmail());
	   msg.setSubject("Flight Ticket");
	   msg.setSentDate(new Date());

	   MimeBodyPart messageBodyPart = new MimeBodyPart();
	   
	   Flight flight=res.getFlight();
	   System.out.println(flight.arrival_time);
	   Passenger passenger=res.getPassenger();
	   
	   messageBodyPart.setContent("<!DOCTYPE html>\r\n" + 
		   		"<html lang=\"en\" xmlns:th=\"http://www.thymeleaf.org\">\r\n" + 
		   		"<head>\r\n" + 
		   		"<meta charset=\"ISO-8859-1\">\r\n" + 
		   		"<link rel=\"stylesheet\"\r\n" + 
		   		"	href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css\"\r\n" + 
		   		"	integrity=\"sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS\"\r\n" + 
		   		"	crossorigin=\"anonymous\">\r\n" + 
		   		"<title>Reservation</title>\r\n" + 
		   		"</head>\r\n" + 
		   		"<body>\r\n" + 
		   		"<div class=\"container\">\r\n" + 
		   		"\r\n" + 
		   		"		<h4>From:</h4>\r\n" + 
		   		"        <h7>"+flight.from+ " </h7>\r\n" + 
		   		"		<h4>To:</h4>\r\n" + 
		   		"		<h7>"+flight.to+" </h7>\r\n" + 
		   		"\r\n" + 
		   		"		<h4>Flight name:</h4>\r\n" + 
		   		"		<h7> "+flight.flight_name+ " </h7>\r\n" + 
		   		"		\r\n" + 
		   		"		<h4>Flight No.:</h4>\r\n" + 
		   		"		<h7> "+flight.flight_number+" </h7>\r\n" + 
		   		"		\r\n" + 
		   		"		<h4>Departure time:</h4>\r\n" + 
		   		"		<h7> "+flight.departure_time+" </h7>\r\n" + 
		   		"		\r\n" + 
		   		"		<h4>Arrival Time:</h4>\r\n" + 
		   		"		<h7> "+flight.arrival_time+" </h7>\r\n" + 
		   		"\r\n" + 
		   		"		<h4>Price:</h4>\r\n" + 
		   		"		<h7> "+flight.price+" </h7>\r\n" + 
		   		"	\r\n" + 
		   		"			<h4>Details of passenger:</h4>\r\n" + 
		   		"	<table border=\"1\">\r\n" + 
		   		"          <tr>\r\n" + 
		   		"             \r\n" + 
		   		"             <th>First_Name</th>    \r\n" + 
		   		"             <th>Last_Name</th>          \r\n" + 
		   		"             <th>Email</th>          \r\n" + 
		   		"             <th>Mobile Number</th>          \r\n" + 
		   		"             <th>Age  </th>              \r\n" + 
		   		"          </tr>	\r\n" + 
		   		"          <tr>\r\n" + 
		   		"          <td>  "+passenger.first_name+"         </td>\r\n" + 
		   		"          <td> "+passenger.last_name+"         </td>\r\n" + 
		   		"          <td>"+passenger.email+"         </td>\r\n" + 
		   		"          <td>"+passenger.number+"         </td>\r\n" + 
		   		"          <td>"+passenger.age+"         </td>\r\n" + 
		   		"          </tr>\r\n" + 
		   		"</body>\r\n" + 
		   		"</html>", "text/html");

	   Multipart multipart = new MimeMultipart();
	   multipart.addBodyPart(messageBodyPart);
	//   MimeBodyPart attachPart = new MimeBodyPart();

	//   attachPart.attachFile("/var/tmp/image19.png");
	//   multipart.addBodyPart(attachPart);
	   msg.setContent(multipart);
	   Transport.send(msg);   
	}
}
