package com.sjsu.grabCab.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.sjsu.grabCab.entity.Admin;
import com.sjsu.grabCab.entity.Driver;

@Repository
public class AdminDAOImpl implements AdminDAO {

	@Autowired
	private Database database;
	
	@Autowired
	private PrepareQuery prepareQuery;
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		prepareQuery.setQuery("select *from Admin where username = ? or email = ?");
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
		Admin a = new Admin((String) rows.get(0).get("username"),(String) rows.get(0).get("password"),(String) rows.get(0).get("email"));
		return (UserDetails) a;
	}

	@Override
	public Admin getAdmin(String username) {
		prepareQuery.setQuery("select *from Admin where username = ? or email = ?");
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
			Admin a = new Admin((String) rows.get(0).get("username"),(String) rows.get(0).get("password"),(String) rows.get(0).get("email"));
			return a;
		}
	}

}
