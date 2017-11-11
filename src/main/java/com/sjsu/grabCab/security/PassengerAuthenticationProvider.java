package com.sjsu.grabCab.security;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sjsu.grabCab.dao.PassengerDAO;
import com.sjsu.grabCab.entity.Passenger;

@Component
public class PassengerAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private PassengerDAO passengerDAO;

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		String username = auth.getName();
		String password = auth.getCredentials().toString();
		//System.out.println("printing type "+request.getAttribute("type"));
		ServletRequestAttributes attrs = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
		System.out.println("printing type "+attrs.getRequest().getParameter("type"));
		if(!attrs.getRequest().getParameter("type").equalsIgnoreCase("passenger")){
			throw new BadCredentialsException("External system authentication failed");
		}
		Passenger p = passengerDAO.getPassenger(username);

		if (p == null) {
			throw new BadCredentialsException("External system authentication failed");
		} else {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			System.out.println(passwordEncoder.encode(password));
			if (passwordEncoder.matches(password, p.getPassword())) {
				return new UsernamePasswordAuthenticationToken(username, passwordEncoder.encode(password), Collections.emptyList());
			} else {
				throw new BadCredentialsException("External system authentication failed");
			}
		}
	}

	@Override
	public boolean supports(Class<?> auth) {
		return auth.equals(UsernamePasswordAuthenticationToken.class);
	}
}
