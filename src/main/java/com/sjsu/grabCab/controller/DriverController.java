package com.sjsu.grabCab.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sjsu.grabCab.dao.DriverDAO;
import com.sjsu.grabCab.entity.Driver;
import com.sjsu.grabCab.entity.Ride;

@Controller
public class DriverController {
	
	@Autowired
	DriverDAO driverDAO;
	
	@RequestMapping(value="/driver", method = RequestMethod.POST)
	public ResponseEntity addDriver(@RequestParam("licenseNumber") String licenseNumber, @RequestParam("email") String email
			,@RequestParam("password") String password, @RequestParam("username") String username, @RequestParam("phone") String phone, @RequestParam("cardnumber") String cardNumber,
			@RequestParam("cardname") String cardName, @RequestParam("expirydate") String expirydate){
		
		if(driverDAO.addUser(licenseNumber, username, password, email, phone, cardNumber, cardName, expirydate))
			return ResponseEntity.ok(null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		
	}
	
	@RequestMapping(value="/driver", method = RequestMethod.GET)
	public ResponseEntity<List> getAllRides(){
		System.out.println("inside get rides");
		List<Map<String, Object>> response = driverDAO.getRides();
		System.out.println("/driver" +response);
			 
			 return ResponseEntity.status(HttpStatus.OK).body(response);	 
		}
	
	@RequestMapping(value="/driver/pending", method = RequestMethod.GET)
	public ResponseEntity getPendingDrivers(){
		    
			return ResponseEntity.status(HttpStatus.OK).body(driverDAO.getPendingDrivers());
	}
	
	@RequestMapping(value="/driver/approve", method = RequestMethod.POST)
	public ResponseEntity approveDriver(@RequestParam("status") String status,@RequestParam("email") String email){
		
		driverDAO.approveDriver(email);
		System.out.println("email is"+email);
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
	@RequestMapping(value="/allDrivers", method = RequestMethod.GET)
	public ResponseEntity<List> getAllDrivers(){
		System.out.println("inside get rides");
		List<Driver> response = driverDAO.getAllDrivers();
			 
		return ResponseEntity.status(HttpStatus.OK).body(response);	 
	}
	
	
}
