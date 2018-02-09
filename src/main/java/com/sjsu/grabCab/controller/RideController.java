package com.sjsu.grabCab.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

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
	public ResponseEntity requestforRide(Principal principal, @RequestParam("pickupLocation") String pickUpLocation, @RequestParam("dropOffLocation") String dropOffLocation, @RequestParam("carType") String carType){
		String email = principal.getName();
		System.out.println("ride request for email"+ email);
		System.out.println("ride request received");
		if(rideDAO.requestRide(email,pickUpLocation, dropOffLocation, carType))
			return ResponseEntity.ok(null);
		return ResponseEntity.status(HttpStatus.OK).body(null);	
	}
	
	@RequestMapping(value="/cancel", method = RequestMethod.POST)
	public ResponseEntity cancelRequestedRide(@RequestParam("Reason") String reason, @RequestParam("rideStatus") String rideStatus){
	
		System.out.println("ride request received");
		if(rideDAO.cancelRide(reason, rideStatus))
			return ResponseEntity.ok(null);
		return ResponseEntity.status(HttpStatus.OK).body(null);	
	}
	
	@RequestMapping(value="/isRideAccepted", method = RequestMethod.GET)
	public ResponseEntity showAcceptedRide(Principal principal){
	    
	    
	    String email = principal.getName();
		System.out.println("ride request received for email"+ email);
		
		
			System.out.println("inside get rides");
			Ride response = rideDAO.getAcceptedRide(email);
			System.out.println("Accepted ride id response :"+response.getId());
			System.out.println("Accepted ride status response :"+response.getRideStatus());
			//System.out.println("Accepted Rides:"+response );
				 return ResponseEntity.status(HttpStatus.OK).body(response);	 
		
	
			
	
	}
	
	@RequestMapping(value="/ride1", method = RequestMethod.POST)
	public ResponseEntity acceptRequestRide(Principal principal,@RequestParam("rideId") String rideId, @RequestParam("rideStatus") String rideStatus){
		String username = principal.getName();
		System.out.println("ride **accept request received" + rideId);
		System.out.println("ride **accept request received" + Long.parseLong(rideId));
		if(rideDAO.acceptRide(username,Long.parseLong(rideId),rideStatus))
				return ResponseEntity.ok(null);
		return ResponseEntity.status(HttpStatus.OK).body(null);
		
	}
	
	
	@RequestMapping(value="/ride2", method = RequestMethod.POST)
	public ResponseEntity endRequestRide(@RequestParam("rideId") String rideId, @RequestParam("rideStatus") String rideStatus){
		
		System.out.println("ride **end request received" + rideId);
		System.out.println("ride **end request received" + Long.parseLong(rideId));
		if(rideDAO.endRide(Long.parseLong(rideId),rideStatus))
				return ResponseEntity.ok(null);
		return ResponseEntity.status(HttpStatus.OK).body(null);
		
	}
	
	
	@RequestMapping(value="/status", method = RequestMethod.GET)	
	public ResponseEntity getRideinprogress() {
		
		System.out.println("ride **getrideinprogress received");
		//System.out.println("ride **get request received" + Long.parseLong(rideId));
		//Ride r= new Ride();
		Ride response = rideDAO.getRide();
		System.out.println("ride id response :"+response.getId());
		System.out.println("ride status response :"+response.getRideStatus());
		return ResponseEntity.status(HttpStatus.OK).body(response);
		
	}
	
	
	
	
	
}
