package com.sjsu.grabCab.entity;

import java.util.Collection;

import javax.persistence.Entity;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Passenger implements UserDetails{
	
	private String username;
	
	private String password;
	
	private String email;
	
	private String phone;

	
	private String passengerClass;
	
	private String promoCode;
	


	public String getEmail() {
		return email;
	}

	
	
	public Passenger(String username, String password, String email, String phone, String passengerClass,
			String promoCode) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.passengerClass = passengerClass;
		this.promoCode = promoCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassengerClass() {
		return passengerClass;
	}

	public void setPassengerClass(String passengerClass) {
		this.passengerClass = passengerClass;
	}

	public String getPromoCode() {
		return promoCode;
	}

	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Passenger() {
		super();
	}
	
	public Passenger(String email, String username, String passengerClass) {
		super();
		this.email = email;
		this.username = username;
		this.passengerClass = passengerClass;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}



	
}
