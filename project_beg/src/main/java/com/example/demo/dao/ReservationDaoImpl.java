package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Flight;
import com.example.demo.model.Passenger;
import com.example.demo.model.Reservation;
import com.example.demo.model.User;
import com.example.demo.service.ReservationService;

@Service
public class ReservationDaoImpl implements ReservationDao {
    
	@Autowired
	public ReservationService reservationService;
	@Override
	public List<Reservation> findByUsers(User user) {
		// TODO Auto-generated method stub
	List<Reservation> res=reservationService.findByUsers(user);
		return res;
	}

	@Override
	public List<Flight> findByUserAndPassenger(User user, Passenger pass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String saveReservation(Reservation res) {
		// TODO Auto-generated method stub
		String message=reservationService.saveReservation(res);
		return message;
	}

	@Override
	public String deleteReservationById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
