package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.Flight;
@Service
public interface FlightDaoInterface {
	public String saveFlight(Flight flight);
	public List<Flight> findByfromAndTo(String from,String to);
	public String updateFlight(Flight flight);
	public String deleteFlight(long id);
	public Optional<Flight> findById(long id);

}
