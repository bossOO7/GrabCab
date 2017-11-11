package com.sjsu.grabCab.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.sjsu.grabCab.entity.Driver;

@Repository
public class DriverDAOImpl implements DriverDAO{
	
	@Autowired
	private Database database;
	
	@Autowired
	private PrepareQuery prepareQuery;
	
	@Override
	public boolean addUser(String username, String password, String email) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String p = passwordEncoder.encode(password);
		String query = "Insert into Driver(username,password,email) values ( \""+username+"\",\""+p+"\",\""+email+"\")";
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

}
