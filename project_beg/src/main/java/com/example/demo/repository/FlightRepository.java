package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Flight;

@Repository
public interface FlightRepository extends CrudRepository<Flight, Long> {

public 	List<Flight> findByFromAndTo(String from,String to);
}
