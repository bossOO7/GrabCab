package com.sjsu.grabCab.dao;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.sjsu.grabCab.entity.Admin;

public interface AdminDAO extends UserDetailsService {
	
	public UserDetails loadUserByUsername(String username);
	
	public Admin getAdmin(String username);
	
}
