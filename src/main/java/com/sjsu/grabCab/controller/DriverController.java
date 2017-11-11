package com.sjsu.grabCab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sjsu.grabCab.dao.DriverDAO;

@Controller
public class DriverController {
	
	@Autowired
	DriverDAO driverDAO;
	
	@RequestMapping(value="/driver", method = RequestMethod.POST)
	public ResponseEntity addPassenger(@RequestParam("email") String email
			,@RequestParam("password") String password, @RequestParam("username") String username){
		
		if(driverDAO.addUser(username, password, email))
			return ResponseEntity.ok(null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		
	}
}
