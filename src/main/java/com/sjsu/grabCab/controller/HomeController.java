package com.sjsu.grabCab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sjsu.grabCab.dao.DriverDAO;
import com.sjsu.grabCab.dao.PassengerDAO;

@Controller
public class HomeController {
	@Autowired
	PassengerDAO passengerDAO;
	
	@Autowired
	DriverDAO driverDAO;
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String showHome(){
		
		return "home.jsp";
	}
	
}
