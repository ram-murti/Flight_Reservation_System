package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Flight;
import com.example.demo.service.FlightService;

@Service
public class FlightDaoImpl implements FlightDaoInterface {
    @Autowired
	public FlightService flightService;
	@Override
	public String saveFlight(Flight flight) {
		// TODO Auto-generated method stub
		String message=flightService.saveFlight(flight);
		System.out.println("FlightDaoImpl-> "+message);
		return message;
	}

	@Override
	public List<Flight> findByfromAndTo(String from, String to) {
		// TODO Auto-generated method stub
		try{
			List<Flight> listOfFlight=flightService.findByfromAndTo(from, to);
		   return listOfFlight;
		}
		catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
		
	}

	@Override
	public String updateFlight(Flight flight) {
		// TODO Auto-generated method stub
		String message=flightService.updateFlight(flight);
		return message;
	}

	@Override
	public String deleteFlight(long id) {
		// TODO Auto-generated method stub
		String message=flightService.deleteFlight(id);
		return message;
	}

	@Override
	public Optional<Flight> findById(long id) {
		// TODO Auto-generated method stub
		try {
			Optional<Flight> flight=flightService.findById(id);
             return flight; 
		} catch (Exception e) {
			// TODO: handle exception
			return null;

		}
	}

}
