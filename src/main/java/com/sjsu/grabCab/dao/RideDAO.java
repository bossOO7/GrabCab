package com.sjsu.grabCab.dao;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface RideDAO extends UserDetailsService{

	Boolean requestRide(Long rideId, String pickUpLocation, String dropOffLocation , String carType);

	Boolean acceptRide(Long rideId, String rideStatus);

	Boolean endRide(Long parseLong, String rideStatus);
}
