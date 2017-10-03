package com.sjsu.grabCab.dao;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface DriverDAO extends UserDetailsService{
	
	String addUser(String username, String password, String email);
	
	public UserDetails loadUserByUsername(String username);
}
