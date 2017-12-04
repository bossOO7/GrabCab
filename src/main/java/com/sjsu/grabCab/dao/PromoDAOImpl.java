package com.sjsu.grabCab.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sjsu.grabCab.entity.Promo;

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

	@Override
	public List<Promo> getPromos() {
		List<Promo> listOfPromos = new ArrayList<Promo>();
		prepareQuery.setQuery("select promocode,discountprice from Promos");
		String query = prepareQuery.getQuery();
		List<Map<String, Object>> rows = null;
		try {
			rows = database.executeQuery(query);
			System.out.println("printing rows from db "+rows);
		} catch (SQLException e) {
			System.out.println("exception received");
		}
		if(rows.size()==0){
			System.out.println("didn't get any rows back");
			return listOfPromos;
		}
		else{
			for(int i=0;i<rows.size();i++){
				Promo p = new Promo((String) rows.get(i).get("promocode"), (Integer)rows.get(i).get("discountprice"));
				listOfPromos.add(p);
			}
		}
		return listOfPromos;
	}

	@Override
	public void assignPromos(List<String> names, String pcode) {
		String q = "update Passenger set promocode = ? where username in (";
		for(int i=0;i<names.size();i++){
			q = q + "?,";
		}
		q = q.substring(0, q.length()-1);
		q = q+")";
		prepareQuery.setQuery(q);
		prepareQuery.substitue(pcode);
		for(int i=0;i<names.size();i++){
			prepareQuery.substitue(names.get(i));
		}
		String query = prepareQuery.getQuery();
		System.out.println("update query"+query);
		try {
			database.executeUpdate(query);
			return;
		} catch (SQLException e) {
			System.out.println("exception received");
		}
		return;
	}

}
