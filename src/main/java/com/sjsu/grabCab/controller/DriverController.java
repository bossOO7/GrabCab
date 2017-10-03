package com.sjsu.grabCab.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DriverController {
	@RequestMapping(value="/driverTest")
	public String driverTest(){
		return "hello driver!!";
	}
}
