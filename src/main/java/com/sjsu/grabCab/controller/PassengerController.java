package com.sjsu.grabCab.controller;

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

import com.sjsu.grabCab.dao.PassengerDAO;
import com.sjsu.grabCab.entity.Passenger;

@Controller
public class PassengerController {
	@Autowired
	PassengerDAO passengerDAO;

	//Raghu: Test method
	
	@RequestMapping(value="/testing", method = RequestMethod.POST)
	public @ResponseBody Passenger test(){
		
		return new Passenger("test","test","test","test",null,null);
		
	}
	
	//Sachin: POST request Passenger Registration
	
	@RequestMapping(value="/passenger", method = RequestMethod.POST)
	public ResponseEntity addPassenger(
			@RequestParam("username") String username, 
			@RequestParam("email") String email, 
			@RequestParam("phone") String phone, 
			@RequestParam("password") String password, 
			@RequestParam("cardnumber") String cardNumber,
			@RequestParam("cardname") String cardName, 
			@RequestParam("expirydate") String expirydate ){
		
		if(passengerDAO.addUser(username, email, phone, password, cardNumber, cardName, expirydate))
			return ResponseEntity.ok(null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}
	
	//Sachin : GET request All passenger
	
	@RequestMapping(value="/passenger", method = RequestMethod.GET)
	public ResponseEntity  getPassengers(){
		
		return ResponseEntity.status(HttpStatus.OK).body(passengerDAO.getAllPassengers());
		
	}
	
	//Sachin: GET request Passenger History
	
	@RequestMapping(value="/passenger/history", method = RequestMethod.GET)
	public ResponseEntity<List> getPassengerHistory(){
		List<Map<String, Object>> response = passengerDAO.getRideHistory();
		 
	    return ResponseEntity.status(HttpStatus.OK).body(response);	
	}

	//Sachin: POST request Rate a Driver
	
	@RequestMapping(value="/ride/ratedriver", method = RequestMethod.POST)
	public ResponseEntity updateDriverRating(
			
			@RequestParam("driverrating") String Rating
			){
		String driverRating = Rating.concat("*");
		if(passengerDAO.updateRide(driverRating))
			return ResponseEntity.ok(null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}
	
	


}
