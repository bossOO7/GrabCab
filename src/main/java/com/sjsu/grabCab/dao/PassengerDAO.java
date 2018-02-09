package com.sjsu.grabCab.dao;

import java.util.List;


import org.springframework.http.ResponseEntity;

import java.util.Map;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.sjsu.grabCab.entity.Passenger;

public interface PassengerDAO  extends UserDetailsService{
	
	Boolean addUser(String username,String email,   String phone, String password, String cardNumber, String cardName, String expirydate);
	
	public UserDetails loadUserByUsername(String username);
	
	public Passenger getPassenger(String username);

	public List<Passenger> getAllPassengers();

	public List<Map<String, Object>> getRideHistory();

	boolean updateRide(String driverRating);

}
