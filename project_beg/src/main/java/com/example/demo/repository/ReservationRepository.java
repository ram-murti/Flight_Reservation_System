package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Flight;
import com.example.demo.model.Passenger;
import com.example.demo.model.Reservation;
import com.example.demo.model.User;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {

	public List<Reservation> findByUser(User user);
	public List<Flight> findByUserAndPassenger(User user,Passenger pass);
}
