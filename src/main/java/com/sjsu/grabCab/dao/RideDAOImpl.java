package com.sjsu.grabCab.dao;

import java.sql.SQLException;
import java.util.*;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;


import com.sjsu.grabCab.entity.Passenger;
import com.sjsu.grabCab.entity.Ride;


import java.sql.SQLException;

@Repository
public class RideDAOImpl implements RideDAO {

	
	@Autowired
	private Database database;
	
	@Autowired
	private PrepareQuery prepareQuery;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("from dao impl" + username);
		System.out.println(username);
		String query = "Select *from Ride where email= ?";
		prepareQuery.setQuery(query);
		prepareQuery.substitue(username);
		prepareQuery.substitue(username);
		query = prepareQuery.getQuery();
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
		Ride r = new Ride((Long) rows.get(0).get("rideId"), (Date) rows.get(0).get("startTime"),(Date) rows.get(0).get("endTime"),(String) rows.get(0).get("pickUpLocation"),(String) rows.get(0).get("dropOffLocation"),(double) rows.get(0).get("cost"), (String) rows.get(0).get("carType"), (String) rows.get(0).get("reason"), (String) rows.get(0).get("rideStatus"), (String) rows.get(0).get("driverRating"), (String) rows.get(0).get("passengerRating"),(String) rows.get(0).get("licenseNumber"),(String) rows.get(0).get("email"));
		return (UserDetails) r;
	}

	@Override
	public Boolean requestRide(Long rideId, String pickUpLocation, String dropOffLocation, String carType) {
		// TODO Auto-generated method stub
		    System.out.println("insert ride request");
			Timestamp tm = new Timestamp(System.currentTimeMillis());
			String query = "Insert into Passenger(rideId,pickUpLocation,dropOffLocation,carType,startTime) values(?,?,?,?,?)";
			prepareQuery.setQuery(query);
			prepareQuery.substitue(rideId);
			prepareQuery.substitue(pickUpLocation);
			prepareQuery.substitue(dropOffLocation);
			prepareQuery.substitue(carType);
			prepareQuery.substitue(tm.getDate());
			query = prepareQuery.getQuery();
			try {
				database.executeUpdate(query);
			} catch (SQLException e) {
				System.out.println("exception received "+e);
				return false;
			}
			return true;
		
	}
	
	public Boolean acceptRide(Long rideId, String rideStatus) {
		System.out.println("update ride status "+rideId);
		
		String query = "Update Ride SET ridestatus = ? where rideid = ?";
		prepareQuery.setQuery(query);
		prepareQuery.substitue(rideStatus);
		prepareQuery.substitue(rideId);
		query = prepareQuery.getQuery();
		try{
			database.executeUpdate(query);
		} catch(SQLException e){
			e.printStackTrace();
			//return false;
		}
		return true;
		
			//Ride r = new Ride((Long) rows.get(0).get("rideId"), (Date) rows.get(0).get("startTime"),(Date) rows.get(0).get("endTime"),(String) rows.get(0).get("pickUpLocation"),(String) rows.get(0).get("dropOffLocation"),(double) rows.get(0).get("cost"), (String) rows.get(0).get("carType"), (String) rows.get(0).get("reason"), (String) rows.get(0).get("rideStatus"), (String) rows.get(0).get("driverRating"), (String) rows.get(0).get("passengerRating"),(String) rows.get(0).get("licenseNumber"),(String) rows.get(0).get("email"));
		
		/*
		 * UPDATE Ride
SET rideId='A'
WHERE rideId='R';
		 * 
		 * */
		
		//return null;
	}

	public Boolean endRide(Long rideId, String rideStatus) {
		System.out.println("end ride status "+rideId);
		
		String query = "Update Ride SET ridestatus = ? where rideid = ?";
		prepareQuery.setQuery(query);
		prepareQuery.substitue(rideStatus);
		prepareQuery.substitue(rideId);
		query = prepareQuery.getQuery();
		try{
			database.executeUpdate(query);
		} catch(SQLException e){
			e.printStackTrace();
			//return false;
		}
		return true;
		
			//Ride r = new Ride((Long) rows.get(0).get("rideId"), (Date) rows.get(0).get("startTime"),(Date) rows.get(0).get("endTime"),(String) rows.get(0).get("pickUpLocation"),(String) rows.get(0).get("dropOffLocation"),(double) rows.get(0).get("cost"), (String) rows.get(0).get("carType"), (String) rows.get(0).get("reason"), (String) rows.get(0).get("rideStatus"), (String) rows.get(0).get("driverRating"), (String) rows.get(0).get("passengerRating"),(String) rows.get(0).get("licenseNumber"),(String) rows.get(0).get("email"));
		
		/*
		 * UPDATE Ride
SET rideId='A'
WHERE rideId='R';
		 * 
		 * */
		
		//return null;
	}

	

}
