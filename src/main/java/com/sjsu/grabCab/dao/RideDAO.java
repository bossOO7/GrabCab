package com.sjsu.grabCab.dao;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetailsService;


import com.sjsu.grabCab.entity.Driver;

import com.sjsu.grabCab.entity.Ride;

public interface RideDAO extends UserDetailsService{

	//Boolean requestRide(Long rideId, String pickUpLocation, String dropOffLocation , String carType);

	Boolean acceptRide(String username, Long rideId, String rideStatus);

	Boolean endRide(Long rideId, String rideStatus);

	//public Ride getRide();
	
	

	Boolean requestRide(String email,String pickUpLocation, String dropOffLocation , String carType);
	
	Boolean cancelRide(String reason, String rideStatus);
	
	public Ride getAcceptedRide(String email);

	public Ride getRide();

}
