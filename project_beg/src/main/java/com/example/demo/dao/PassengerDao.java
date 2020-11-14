package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.Passenger;
import com.example.demo.model.User;

@Service
public interface PassengerDao  {
	public String savePassenger(Passenger pass);
	public Optional<Passenger> findById(long id );
	public String updatePassenger(Passenger passenger);
	public String deletePassenger(long id);
	public List<Passenger> findByUser(User user);

}
