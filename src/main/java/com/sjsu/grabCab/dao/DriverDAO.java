package com.sjsu.grabCab.dao;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.sjsu.grabCab.entity.Driver;
import com.sjsu.grabCab.entity.Passenger;
import com.sjsu.grabCab.entity.Ride;

public interface DriverDAO extends UserDetailsService{
	
	boolean addUser(String licensenumber,String username, String password, String email, String phone);
	
	public UserDetails loadUserByUsername(String username);
	
	public Driver getDriver(String username);
	
	public Ride getRides(String rideStatus);
}
