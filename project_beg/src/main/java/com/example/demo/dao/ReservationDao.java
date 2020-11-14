package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Flight;
import com.example.demo.model.Passenger;
import com.example.demo.model.Reservation;
import com.example.demo.model.User;

@Service
public interface ReservationDao {
	public List<Reservation> findByUsers(User user);
	public List<Flight> findByUserAndPassenger(User user,Passenger pass);
	public String saveReservation(Reservation res);
	public String deleteReservationById(long id);
}
