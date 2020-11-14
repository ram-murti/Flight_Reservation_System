package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Passenger;
import com.example.demo.model.User;
import com.example.demo.service.PassengerService;

@Service
public class PassengerDaoImpl implements PassengerDao {
    @Autowired
	public PassengerService passengerService;
	@Override
	public String savePassenger(Passenger pass) {
		// TODO Auto-generated method stub
		String message=passengerService.savePassenger(pass);
		
		return message;
	}

	@Override
	public Optional<Passenger> findById(long id) {
		// TODO Auto-generated method stub
		try {
		Optional<Passenger> pass=passengerService.findById(id);
				return pass;
				}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return null;
		}
		
	}

	@Override
	public String updatePassenger(Passenger passenger) {
		// TODO Auto-generated method stub
		String message=passengerService.updatePassenger(passenger);
		return message;
	}

	@Override
	public String deletePassenger(long id) {
		// TODO Auto-generated method stub
		String message=passengerService.deletePassenger(id);
		return message;
	}
	@Override
	public List<Passenger> findByUser(User user) {
		// TODO Auto-generated method stub
		
	List<Passenger> listOfPassAddedWithUser=passengerService.findByUser(user);
		
		
		return listOfPassAddedWithUser;
	}
}
