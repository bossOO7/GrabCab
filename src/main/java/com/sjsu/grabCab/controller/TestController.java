package com.sjsu.grabCab.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	@RequestMapping(value="/test2", method = RequestMethod.POST)
	@ResponseBody
	public String greet(Principal principal)
	{
		return "hello "+principal.getName();
	}
}
