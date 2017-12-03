package com.sjsu.grabCab.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.sjsu.grabCab.entity.Driver;
import com.sjsu.grabCab.entity.Ride;

@Repository
public class DriverDAOImpl implements DriverDAO{
	
	@Autowired
	private Database database;
	
	@Autowired
	private PrepareQuery prepareQuery;
	
	@Override
	public boolean addUser(String licensenumber,String username, String password, String email,String phone) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String p = passwordEncoder.encode(password);
		System.out.println(p);
		String query = "Insert into Driver(licensenumber,username,password,email,phone) values ( \""+licensenumber+"\",\""+username+"\",\""+p+"\",\""+email+"\",\""+phone+"\")";
		try {
			database.executeUpdate(query);
			return true;
		} catch (SQLException e) {
			System.out.println("exception received");
		}
		return false;
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		System.out.println("from dao impl"+username);
		System.out.println(username);
		prepareQuery.setQuery("select *from Driver where username = ? or email = ?");
		prepareQuery.substitue(username);
		prepareQuery.substitue(username);
		String query = prepareQuery.getQuery();
		List<Map<String, Object>> rows = null;
		try {
			 rows = database.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(rows.isEmpty());
		if(rows.size()==0){
			//throw new Exception("No user found with that username");
		}
		Driver d = new Driver((String) rows.get(0).get("username"),(String) rows.get(0).get("password"),(String) rows.get(0).get("email"));
		return (UserDetails) d;
	}

	@Override
	public Driver getDriver(String username) {
		prepareQuery.setQuery("select *from Driver where username = ? or email = ?");
		prepareQuery.substitue(username);
		prepareQuery.substitue(username);
		String query = prepareQuery.getQuery();
		List<Map<String, Object>> rows = null;
		try{
			rows = database.executeQuery(query);
		} catch(SQLException e){
			e.printStackTrace();
		}
		if(rows.size()==0){
			System.out.println("didn't get any rows back");
			return null;
		}
		else{
			Driver d = new Driver((String) rows.get(0).get("username"),(String) rows.get(0).get("password"),(String) rows.get(0).get("email"));
			return d;
		}
	}
	
	@Override
	public List<Map<String, Object>> getRides() {
		prepareQuery.setQuery("select * from Ride where ridestatus = 'R' ");
		
		String query = prepareQuery.getQuery();
		List<Map<String, Object>> rows = null;
		try{
			rows = database.executeQuery(query);
			System.out.println("printing rows from db "+rows);
		} catch(SQLException e){
			e.printStackTrace();
		}
		if(rows.size()==0){
			System.out.println("didn't get any rows back");
			return null;
		}
		else{
			//Ride r = new Ride((Long) rows.get(0).get("rideId"), (Date) rows.get(0).get("startTime"),(Date) rows.get(0).get("endTime"),(String) rows.get(0).get("pickUpLocation"),(String) rows.get(0).get("dropOffLocation"),(double) rows.get(0).get("cost"), (String) rows.get(0).get("carType"), (String) rows.get(0).get("reason"), (String) rows.get(0).get("rideStatus"), (String) rows.get(0).get("driverRating"), (String) rows.get(0).get("passengerRating"),(String) rows.get(0).get("licenseNumber"),(String) rows.get(0).get("email"));
			//Ride r = new Ride((Long) rows.get(0).get("rideId"), (Date) rows.get(0).get("startTime"),(Date) rows.get(0).get("endTime"),(String) rows.get(0).get("pickUpLocation"),(String) rows.get(0).get("dropOffLocation"),(double) rows.get(0).get("cost"), (String) rows.get(0).get("carType"), (String) rows.get(0).get("reason"), (String) rows.get(0).get("rideStatus"), (String) rows.get(0).get("driverRating"), (String) rows.get(0).get("passengerRating"),(String) rows.get(0).get("licenseNumber"),(String) rows.get(0).get("email"));
			
			//System.out.println(" Get Ride Results : "+r.getRideStatus()+":"+r.getId());
			return rows;
		}
	}

}
