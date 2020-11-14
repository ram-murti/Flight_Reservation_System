package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Flight;
import com.example.demo.model.Passenger;
import com.example.demo.model.Reservation;
import com.example.demo.model.User;
import com.example.demo.repository.ReservationRepository;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
	public ReservationRepository reservationRepository;
	@Override
	public List<Reservation> findByUsers(User user) {
		// TODO Auto-generated method stub
		try {
			System.out.println("here");
			List<Reservation> reserve=reservationRepository.findByUser(user);
			System.out.println("here");
		return  reserve;
		}
		catch (Exception e) {
       // TODO: handle exception
			System.out.println(e);
			return null;
			
		}
	}

	@Override
	public List<Flight> findByUserAndPassenger(User user, Passenger pass) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public String saveReservation(Reservation res) {
		// TODO Auto-generated method stub
		try {
		reservationRepository.save(res);
		return "saved successfully";
		}catch (Exception e) {
			// TODO: handle exception
			
			return "something went wrong";

		}
		
	}

	@Override
	public String deleteReservationById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
