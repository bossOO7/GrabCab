package com.sjsu.grabCab.dao;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface RideDAO extends UserDetailsService{

	Boolean requestRide(Long rideId, String pickUpLocation, String dropOffLocation , String carType);
}
