package com.sjsu.grabCab.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Driver implements UserDetails{

	
	private String licenseNumber;
	
	
	private String username;
	
	
	private String password;
	
	
	private String email;
	
	private String status;
	
	private String driverClass;
	

	public Driver(String email, String username, String driverClass) {
		super();
		this.email = email;
		this.username = username;
		this.driverClass = driverClass;
	}
	
	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLicensenumber() {
		return licenseNumber;
	}

	public void setLicensenumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	private String phone;
	
	public Driver(String username, String password, String email,String status) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.status = status;
	}

	public Driver() {
		super();
	}

	public Driver(String licenseNumber, String username, String email, String status, String phone) {
		super();
		this.licenseNumber = licenseNumber;
		this.username = username;
		this.email = email;
		this.status = status;
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUsername(String username) {
		this.username = username;
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
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

}
