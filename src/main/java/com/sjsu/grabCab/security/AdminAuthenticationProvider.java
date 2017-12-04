package com.sjsu.grabCab.security;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sjsu.grabCab.dao.AdminDAO;
import com.sjsu.grabCab.entity.Admin;

@Component
public class AdminAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	private AdminDAO adminDAO;
	
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		String username = auth.getName();
		String password = auth.getCredentials().toString();
		ServletRequestAttributes attrs = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
		System.out.println("printing type "+attrs.getRequest().getParameter("type"));
		if(!attrs.getRequest().getParameter("type").equalsIgnoreCase("admin")){
			throw new BadCredentialsException("External system authentication failed");
		}
		
		Admin a = adminDAO.getAdmin(username);
		if (a == null) {
			throw new BadCredentialsException("External system authentication failed");
		} else {
			System.out.println(a.getUsername()+a.getPassword());
			//BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			if (password.equals(a.getPassword())) {
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
