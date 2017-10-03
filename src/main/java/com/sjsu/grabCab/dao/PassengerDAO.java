package com.sjsu.grabCab.dao;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.sjsu.grabCab.entity.Passenger;

public interface PassengerDAO  extends UserDetailsService{
	
	String addUser(String username, String password, String email);
	
	public UserDetails loadUserByUsername(String username);
	
	public Passenger getPassenger(String username);
}
