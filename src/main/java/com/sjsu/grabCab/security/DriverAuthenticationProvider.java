package com.sjsu.grabCab.security;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.WebApplicationContext;

import com.sjsu.grabCab.dao.DriverDAO;
import com.sjsu.grabCab.dao.PassengerDAO;
import com.sjsu.grabCab.entity.Driver;
import com.sjsu.grabCab.entity.Passenger;

@Component
public class DriverAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private DriverDAO driverDAO;

//	@Autowired 
//	private HttpServletRequest request;
//	

	
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		String username = auth.getName();
		String password = auth.getCredentials().toString();
		
		ServletRequestAttributes attrs = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
		System.out.println("printing type "+attrs.getRequest().getParameter("type"));
		Driver p = driverDAO.getDriver(username);

		if (p == null) {
			throw new BadCredentialsException("External system authentication failed");
		} else {
			System.out.println(p.getUsername()+p.getPassword());
			if (p.getUsername().equals(username) && p.getPassword().equals(password)) {
				return new UsernamePasswordAuthenticationToken(username, password, Collections.emptyList());
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
