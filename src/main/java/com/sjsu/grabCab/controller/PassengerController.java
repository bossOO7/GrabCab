package com.sjsu.grabCab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class PassengerController {

	@RequestMapping(value="/login")
	public String login(){
		return "callSuccess";
	}
	
	@RequestMapping(value="/test")
	public String test(){
		return "test";
	}
}
