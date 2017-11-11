package com.sjsu.grabCab.controller;


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
	
	@RequestMapping(value="/testing", method = RequestMethod.POST)
	public @ResponseBody Passenger test(){
		
		return new Passenger("test","test","test");
		
	}
	
	@RequestMapping(value="/passenger", method = RequestMethod.POST)
	public ResponseEntity addPassenger(@RequestParam("email") String email
			,@RequestParam("password") String password, @RequestParam("username") String username){
		
		if(passengerDAO.addUser(username, password, email))
			return ResponseEntity.ok(null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		
	}
	
	
}
