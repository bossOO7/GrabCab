package com.sjsu.grabCab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sjsu.grabCab.dao.RideDAO;
import com.sjsu.grabCab.entity.Passenger;
import com.sjsu.grabCab.entity.Ride;

@Controller
public class RideController {
	@Autowired
	RideDAO rideDAO;
	
	@RequestMapping(value="/test", method = RequestMethod.POST)
	public @ResponseBody Ride test(){
	
	return new Ride(null, null, null, "test","test",0, "test", null, null, null, null, null, null);
		
	}
	
//	@RequestMapping(value="/ride", method = RequestMethod.POST)
//	public ResponseEntity requestforRide(@RequestParam("rideId") Long rideId, @RequestParam("pickUpLocation") String pickUpLocation, @RequestParam("dropOffLocation") String dropOffLocation, @RequestParam("carType") String carType){
//	
//		if(rideDAO.requestRide(rideId, pickUpLocation, dropOffLocation, carType))
//			return ResponseEntity.ok(null);
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//		
//	}
	
	@RequestMapping(value="/ride", method = RequestMethod.POST)
	public ResponseEntity requestforRide(@RequestParam("pickupLocation") String pickupLocation, @RequestParam("dropOffLocation") String dropOffLocation, @RequestParam("carType") String carType){
	
		System.out.println("ride request received");
		return ResponseEntity.status(HttpStatus.OK).body(null);
		

		
	}
	
}
