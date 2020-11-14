package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table
public class Airports {

	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	public long id;
	public String IATA_code;
	
	public String ICAO_code;
	public String airport_name;
	public String city_name;
}


	