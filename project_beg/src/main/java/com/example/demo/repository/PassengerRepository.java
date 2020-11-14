package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Passenger;
import com.example.demo.model.User;

@Repository
public interface PassengerRepository extends CrudRepository<Passenger, Long>  {
  
	public List<Passenger> findByUser(User user);
}
