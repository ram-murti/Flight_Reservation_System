package com.example.demo.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="flightt")
public class Flight {

	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	public Long id;
	public String flight_number;
	public String flight_name;
	@Column(name = "fromm")
	public String from;
	public String to;
	public String seat_capacity;
	public String departure_time;
	public String arrival_time;
	public String price;
	@OneToMany(mappedBy = "flight")
	public List<Reservation> reservation;

	

	public Flight(String flight_number, String flight_name, String from, String to, String seat_capacity,
			String departure_time, String arrival_time, String price, List<Reservation> reservation) {
		super();
		this.flight_number = flight_number;
		this.flight_name = flight_name;
		this.from = from;
		this.to = to;
		this.seat_capacity = seat_capacity;
		this.departure_time = departure_time;
		this.arrival_time = arrival_time;
		this.price = price;
		this.reservation = reservation;
	}

	public Flight() {
		// TODO Auto-generated constructor stub
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFlight_number() {
		return flight_number;
	}
	public void setFlight_number(String flight_number) {
		this.flight_number = flight_number;
	}
	public String getFlight_name() {
		return flight_name;
	}
	public void setFlight_name(String flight_name) {
		this.flight_name = flight_name;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getSeat_capacity() {
		return seat_capacity;
	}
	public void setSeat_capacity(String seat_capacity) {
		this.seat_capacity = seat_capacity;
	}
	public String getDeparture_time() {
		return departure_time;
	}
	public void setDeparture_time(String departure_time) {
		this.departure_time = departure_time;
	}
	public String getArrival_time() {
		return arrival_time;
	}
	public void setArrival_time(String arrival_time) {
		this.arrival_time = arrival_time;
	}

	
}
