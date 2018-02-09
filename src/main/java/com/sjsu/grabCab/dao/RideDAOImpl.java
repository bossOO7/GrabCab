package com.sjsu.grabCab.dao;

import java.sql.SQLException;

import java.util.*;
import java.util.List;
import java.util.Map;
import java.util.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.sjsu.grabCab.entity.Driver;
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
		System.out.println("rows result in status:"+rows);
		Ride r = new Ride((Long) rows.get(0).get("rideId"), (Date) rows.get(0).get("startTime"),(Date) rows.get(0).get("endTime"),(String) rows.get(0).get("pickUpLocation"),(String) rows.get(0).get("dropOffLocation"),(double) rows.get(0).get("cost"), (String) rows.get(0).get("carType"), (String) rows.get(0).get("reason"), (String) rows.get(0).get("rideStatus"), (String) rows.get(0).get("driverRating"), (String) rows.get(0).get("passengerRating"),(String) rows.get(0).get("licenseNumber"),(String) rows.get(0).get("email"));
		return (UserDetails) r;
	}

	@Override
	public Boolean requestRide(String email, String pickUpLocation, String dropOffLocation, String carType) {
		// TODO Auto-generated method stub
		    System.out.println("insert ride request");
		 
			String query = "Insert into Ride(email,pickuplocation,dropofflocation,cartype) values(?,?,?,?)";
			prepareQuery.setQuery(query);
			prepareQuery.substitue(email);
			prepareQuery.substitue(pickUpLocation);
			prepareQuery.substitue(dropOffLocation);
			prepareQuery.substitue(carType);
			query = prepareQuery.getQuery();
			try {
				database.executeUpdate(query);
			} catch (SQLException e) {
				System.out.println("exception received "+e);
				return false;
			}
			
			
			return true;
		
	}
	
	public Boolean acceptRide(String username, Long rideId, String rideStatus) {
		System.out.println("update ride status "+rideId);
		
		String query = "Select * from Driver where username = ?";
		prepareQuery.setQuery(query);
		prepareQuery.substitue(username);
		List<Map<String, Object>> rows = null;
		
		query = prepareQuery.getQuery();
		try{
			rows = database.executeQuery(query);
		} catch(SQLException e){
			e.printStackTrace();
			//return false;
		}
		Driver d = new Driver((String)rows.get(0).get("licensenumber"),
				(String) rows.get(0).get("username"),
				(String) rows.get(0).get("email"),
				(String) rows.get(0).get("status"),
				(String) rows.get(0).get("phone"));
		
		String licensenumber = d.getLicensenumber();
		
		query = "Update Ride SET ridestatus = ?,licensenumber = ? where rideid = ?";
		prepareQuery.setQuery(query);
		prepareQuery.substitue(rideStatus);
		prepareQuery.substitue(licensenumber);
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
		
		query = "Select * from Ride where rideid = ?";
		prepareQuery.setQuery(query);
		prepareQuery.substitue(rideId);
		List<Map<String, Object>> rows = null;
		
		query = prepareQuery.getQuery();
		try{
			rows = database.executeQuery(query);
		} catch(SQLException e){
			e.printStackTrace();
			//return false;
		}
		if(rows.size()==0){
			//throw new Exception("No user found with that username");
		}
		
		int rideid = (int) rows.get(0).get("rideid");
	    rideStatus =  (String) rows.get(0).get("ridestatus");
		String email =  (String) rows.get(0).get("email");
		
		System.out.println("rideid before 1st stored procedure call"+rideid);
		//Ride r = new Ride(rideid, rideStatus, email);
		System.out.println("rideid before 1st stored procedure call"+rideid);
		
		try{
			System.out.println("rideid in 1st stored procedure call"+rideid);
			 database.executeStoredProcedureSetCost(rideid);
			
		} catch(SQLException e){
			e.printStackTrace();
			//return false;
		}
		
		
		
		prepareQuery.setQuery(email);
		email = prepareQuery.getQuery();
		try{
			System.out.println("Email in  2nd stored procedure call"+email);
			 database.executeStoredProcedure(email, rideid);
			
		} catch(SQLException e){
			e.printStackTrace();
			//return false;
		}
		
		String pickUpLocation = (String) rows.get(0).get("pickuplocation");
		String dropOffLocation = (String) rows.get(0).get("dropofflocation");
		String licenseNumber = (String) rows.get(0).get("licensenumber");
		String carType = (String) rows.get(0).get("cartype");
		email = (String) rows.get(0).get("email");
		rideStatus = (String) rows.get(0).get("ridestatus");
		 rideid = (int) rows.get(0).get("rideid");
		/*
		query = "Insert into Ride (rideid, email, pickUpLocation, dropOffLocation, carType, licenseNumber, rideStatus) values(?,?,?,?,?,?,?)";
		prepareQuery.setQuery(query);
		prepareQuery.substitue(rideId);
		prepareQuery.substitue(email);
		prepareQuery.substitue(pickUpLocation);
		prepareQuery.substitue(dropOffLocation);
		prepareQuery.substitue(carType);
		prepareQuery.substitue(licenseNumber);
		prepareQuery.substitue(rideStatus);
		
		query = prepareQuery.getQuery();
		*/
		try{
		    database.executeCassandra(rideid,email,pickUpLocation,dropOffLocation,carType,licenseNumber,rideStatus);
		}catch(SQLException e){
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

	
	
	/*
	public Ride getRideinprogress() {
		//prepareQuery.setQuery("select *from Driver where username = ? or email = ?");
		//prepareQuery.substitue(username);
		//prepareQuery.substitue(username);
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
			Ride r = new Ride(rows.get(0).get("rideid"), rows.get(0).get("startTime"), rows.get(0).get("endTime"), (String) rows.get(0).get(""),(String) rows.get(0).get("password"),0, (String) rows.get(0).get("email"), query, query, query, query, query, query);
			return r;
		}
	}
	*/

	@Override
	public Boolean cancelRide(  String reason, String rideStatus) {
		// TODO Auto-generated method stub
		System.out.println("update/cancel ride request");
		String query = "Update Ride set reason = ?, ridestatus = 'X' where ridestatus IN ('A','R') ";
		prepareQuery.setQuery(query);
		prepareQuery.substitue(reason);
		//prepareQuery.substitue(rideStatus);
		
		query = prepareQuery.getQuery();
		try {
			database.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("exception received "+e);
			return false;
		}
		return true;
	}
	
	public Ride getAcceptedRide(String email) {
		// TODO Auto-generated method stub
		    
		   
			 
			prepareQuery.setQuery("select * from Ride Where  ridestatus IN ('A','R') ");
			
			//prepareQuery.substitue(rideStatus);
			
			//prepareQuery.substitue(email);
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
				String rideStatus = "C";
				Ride r = new Ride(rideStatus);
				return r;
			}
			else{
				
				Long rideid = Long.valueOf((int) rows.get(0).get("rideid"));
				String rideStatus =  (String) rows.get(0).get("ridestatus");
				
				Ride r = new Ride(rideid, rideStatus);
				
				return r;
			}
			
	}
	
	public Ride getRide() {
		// TODO Auto-generated method stub
		    
		   
			 System.out.println("Inside ride status:");
			prepareQuery.setQuery("select * from Ride Where ridestatus = 'A' ");
			
			//prepareQuery.substitue(rideStatus);
			
			
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
				Ride r = new Ride();
				return r;
			}
			else{
				
				Long rideid = Long.valueOf((int) rows.get(0).get("rideid"));
				String rideStatus =  (String) rows.get(0).get("ridestatus");
				System.out.println("printing rideid from getride : rows conversion "+rideid);
				System.out.println("printing ridestatus from getride : rows conversion "+rideStatus);
				Ride r = new Ride(rideid, rideStatus );
				System.out.println("printing rideid from getride : rows conversion "+rideid);
				System.out.println("printing ridestatus from getride : rows conversion "+rideStatus);
				return r;
			}
			
	}

}
