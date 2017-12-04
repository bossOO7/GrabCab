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

import com.sjsu.grabCab.dao.DriverDAO;
import com.sjsu.grabCab.entity.Driver;


@Component
public class DriverAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private DriverDAO driverDAO;
	
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		String username = auth.getName();
		String password = auth.getCredentials().toString();
		
		ServletRequestAttributes attrs = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
		System.out.println("printing type "+attrs.getRequest().getParameter("type"));
		if(!attrs.getRequest().getParameter("type").equalsIgnoreCase("driver")){
			throw new BadCredentialsException("External system authentication failed");
		}
		Driver d = driverDAO.getDriver(username);

		if (d == null) {
			throw new BadCredentialsException("External system authentication failed");
		} else {
			System.out.println(d.getUsername()+d.getPassword());
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			if (passwordEncoder.matches(password, d.getPassword())) {
				if(d.getStatus().equals("approved")){
					return new UsernamePasswordAuthenticationToken(username, password, Collections.emptyList());
				}
				else{
					throw new BadCredentialsException("External system authentication failed");
				}
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
