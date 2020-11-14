package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Flight;
import com.example.demo.repository.FlightRepository;
@Service
public class FlightServiceImpl implements FlightService {
     @Autowired
     public FlightRepository flightRepository;
     
     
	@Override
	public String saveFlight(Flight flight) {
		// TODO Auto-generated method stub
		System.out.println("FlightServiceImpl->");

		try {
		Flight varFlight=flightRepository.save(flight);
		return "succusfully saved";
		}
		catch(Exception e) {
			return "something went wrong";
		}
		
	
	}

	@Override
	public List<Flight> findByfromAndTo(String from, String to) {
		// TODO Auto-generated method stub
		try {
		List<Flight> flight=flightRepository.findByFromAndTo(from, to);
		return flight;}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return null;
		}
	}

	@Override
	public String updateFlight(Flight flight) {
		// TODO Auto-generated method stub
		try {
			flightRepository.save(flight);
			return "updated successfully";
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return "something went wrong";
		}
		
	}

	@Override
	public String deleteFlight(long id) {
		// TODO Auto-generated method stub
		try {
			flightRepository.deleteById(id);
            return "deleted successfully";
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return "something went wrong";
		}
		
	}

	@Override
	public Optional<Flight> findById(long id) {
		// TODO Auto-generated method stub
		try {
		Optional<Flight> flight=flightRepository.findById(id);
		
		return flight;

		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	
	}
	
	

}
