package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Passenger;
import com.example.demo.model.User;
import com.example.demo.repository.PassengerRepository;

@Service
public class PassengerServiceImpl implements PassengerService {
    @Autowired
	public PassengerRepository passengerRepository;
	@Override
	public String savePassenger(Passenger pass) {
		// TODO Auto-generated method stub
		try {
			passengerRepository.save(pass);
			return "Passenger Added";
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return "something went wrong try again";

		}
	}

	@Override
	public Optional<Passenger> findById(long id) {
		// TODO Auto-generated method stub
		try {
			Optional<Passenger> pass=passengerRepository.findById(id);
			return pass;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return null;
	
		}
	}

	@Override
	public String updatePassenger(Passenger passenger) {
		// TODO Auto-generated method stub
		try {
			passengerRepository.save(passenger);
			return "Passenger added";
		} catch (Exception e) {
			// TODO: handle exception
			return "something went wrong try again";

		}
	}

	@Override
	public String deletePassenger(long id) {
		// TODO Auto-generated method stub
		try {
			passengerRepository.deleteById(id);
			return "deleted successfully";
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return "something went wrong try again";
		}
	
	}

	@Override
	public List<Passenger> findByUser(User user) {
		// TODO Auto-generated method stub
		
	List<Passenger> listOfPassAddedWithUser=passengerRepository.findByUser(user);
		
		
		return listOfPassAddedWithUser;
	}

}
