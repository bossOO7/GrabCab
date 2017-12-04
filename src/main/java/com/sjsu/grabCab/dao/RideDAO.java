package com.sjsu.grabCab.dao;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.sjsu.grabCab.entity.Driver;
import com.sjsu.grabCab.entity.Ride;

public interface RideDAO extends UserDetailsService{

	Boolean requestRide(Long rideId, String pickUpLocation, String dropOffLocation , String carType);

	Boolean acceptRide(Long rideId, String rideStatus);

	Boolean endRide(Long rideId, String rideStatus);

	//public Ride getRide();
	
	
}
