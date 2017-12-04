package com.sjsu.grabCab.dao;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PromoDAOImpl implements PromoDAO{

	@Autowired
	private Database database;
	
	@Autowired
	private PrepareQuery prepareQuery;
	
	@Override
	public boolean addPromo(String promocode, int discountprice, String email) {
		prepareQuery.setQuery("insert into Promos(promocode,discountprice,email) values (?,?,?)");
		prepareQuery.substitue(promocode);
		prepareQuery.substitue(discountprice);
		prepareQuery.substitue(email);
		String query = prepareQuery.getQuery();
		try {
			database.executeUpdate(query);
			return true;
		} catch (SQLException e) {
			System.out.println("exception received");
		}
		return false;
	}

}
