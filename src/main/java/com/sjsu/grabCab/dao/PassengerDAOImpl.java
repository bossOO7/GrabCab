package com.sjsu.grabCab.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sjsu.grabCab.entity.Passenger;

@Repository
public class PassengerDAOImpl implements PassengerDAO {

	@Autowired
	private Database database;
	
	@Autowired
	private PrepareQuery prepareQuery;

	@Override
	public Boolean addUser( String username,String email, String phone, String password,String cardNumber, String cardName, String expirydate) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String p = passwordEncoder.encode(password);
		System.out.println("Insert Into Passenger: Adding Passenger" + username);
		String query = "Insert into Passenger(email,username,phone,password) values(?,?,?,?)";
		
		prepareQuery.setQuery(query);
		prepareQuery.substitue(email);
		prepareQuery.substitue(username);
		prepareQuery.substitue(phone);
		prepareQuery.substitue(p);
		
		query = prepareQuery.getQuery();
		try {
			database.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("exception received "+e);
			return false;
		}
		
		
		
		
		
		System.out.println("Insert Into Card: Adding Passenger" + username);
		query = "Insert into Carddetails(email,cardnumber,cardname,expirydate) values(?,?,?,?)";
		
		prepareQuery.setQuery(query);
		prepareQuery.substitue(email);
		prepareQuery.substitue(cardNumber);
		prepareQuery.substitue(cardName);
		prepareQuery.substitue(expirydate);
		
		query = prepareQuery.getQuery();
		try {
			database.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("exception received "+e);
			return false;
		}
		return true;
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		System.out.println("Select from Passenger: Get Passenger  " + username);
		System.out.println(username);
		String query = "Select *from Passenger where username = ? or email= ?";
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

		Passenger p = new Passenger((String) rows.get(0).get("username"),(String) rows.get(0).get("password"),(String) rows.get(0).get("email"), (String) rows.get(0).get("phone"), (String) rows.get(0).get("promoCode"), (String) rows.get(0).get("pssengerClass"));

		return (UserDetails) p;
	}

	@Override
	public Passenger getPassenger(String username) {
		String query = "select *from Passenger where email = ?";
		prepareQuery.setQuery(query);
		prepareQuery.substitue(username);
		//prepareQuery.substitue(username);
		query = prepareQuery.getQuery();
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

			Passenger p = new Passenger((String) rows.get(0).get("username"),(String) rows.get(0).get("password"),(String) rows.get(0).get("email"), (String) rows.get(0).get("phone"),(String) rows.get(0).get("passengerclass"),(String) rows.get(0).get("promocode"));

			return p;
		}
	}

	@Override
	public List<Passenger> getAllPassengers() {
		String query = "select email,username,class from Passenger";
		prepareQuery.setQuery(query);
		query = prepareQuery.getQuery();
		List<Map<String, Object>> rows = null;
		List<Passenger> listOfPassengers = new ArrayList<Passenger>();
		try{
			rows = database.executeQuery(query);
		} catch(SQLException e){
			e.printStackTrace();
		}
		if(rows.size()==0){
			System.out.println("didn't get any rows back");
			return listOfPassengers;
		}else{
			for(int i=0;i<rows.size();i++){
				Passenger p = new Passenger((String) rows.get(i).get("email"),(String) rows.get(i).get("username"),(String) rows.get(i).get("class"));
				listOfPassengers.add(p);
			}
		}
		return listOfPassengers;
	}
	public List<Map<String, Object>> getRideHistory() {
		// TODO Auto-generated method stub
		
		
			prepareQuery.setQuery("select * from Passenger_History ");
			
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
				
				return rows;
			}
			
	}

	@Override
	public boolean updateRide(String driverRating) {
		// TODO Auto-generated method stub
		System.out.println("update driver rating");
		String query = "Update Ride set driverrating = ? WHERE driverrating IS NULL ";
		prepareQuery.setQuery(query);
		prepareQuery.substitue(driverRating);
		;
		
		query = prepareQuery.getQuery();
		try {
			database.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("exception received "+e);
			return false;
		}
		return true;
		
	}

}
