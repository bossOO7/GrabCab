package com.sjsu.grabCab.security;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.sjsu.grabCab.dao.PassengerDAO;
import com.sjsu.grabCab.entity.Passenger;

@Component
public class PassengerAuthenticationProvider implements AuthenticationProvider{
	
	@Autowired
	private PassengerDAO passengerDAO;
	
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		String username = auth.getName();
		String password = auth.getCredentials().toString();
		
		Passenger p = passengerDAO.getPassenger(username);
		
		if (p.getUsername().equals(username) && p.getPassword().equals(password)) {
			return new UsernamePasswordAuthenticationToken(username, password, Collections.emptyList());
		} else {
			throw new BadCredentialsException("External system authentication failed");
		}
	}

	@Override
	public boolean supports(Class<?> auth) {
		return auth.equals(UsernamePasswordAuthenticationToken.class);
	}
}
