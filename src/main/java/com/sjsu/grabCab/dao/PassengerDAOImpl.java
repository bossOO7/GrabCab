package com.sjsu.grabCab.dao;

import java.sql.SQLException;
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
	public Boolean addUser(String username, String password, String email, String phone) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String p = passwordEncoder.encode(password);
		String query = "Insert into Passenger(username,password,email,phone) values(?,?,?,?)";
		prepareQuery.setQuery(query);
		prepareQuery.substitue(username);
		prepareQuery.substitue(p);
		prepareQuery.substitue(email);
		prepareQuery.substitue(phone);
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
		System.out.println("from dao impl" + username);
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
		Passenger p = new Passenger((String) rows.get(0).get("username"),(String) rows.get(0).get("password"),(String) rows.get(0).get("email"),(String) rows.get(0).get("phone"));
		return (UserDetails) p;
	}

	@Override
	public Passenger getPassenger(String username) {
		String query = "select *from Passenger where username = ? or email = ?";
		prepareQuery.setQuery(query);
		prepareQuery.substitue(username);
		prepareQuery.substitue(username);
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
			Passenger p = new Passenger((String) rows.get(0).get("username"),(String) rows.get(0).get("password"),(String) rows.get(0).get("email"),(String) rows.get(0).get("phone"));
			return p;
		}
	}

}
