package com.sjsu.grabCab.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sjsu.grabCab.entity.Bonus;
import com.sjsu.grabCab.entity.Promo;

@Repository
public class BonusDAOImpl implements BonusDAO{

	@Autowired
	private Database database;
	
	@Autowired
	private PrepareQuery prepareQuery;
	
	@Override
	public boolean addBonus(String bonuscode, int amount, String email) {
		prepareQuery.setQuery("insert into Bonus(bonuscode,amount,email) values (?,?,?)");
		prepareQuery.substitue(bonuscode);
		prepareQuery.substitue(amount);
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
	public List<Bonus> getBonuses() {
		List<Bonus> listOfBonus = new ArrayList<Bonus>();
		prepareQuery.setQuery("select bonuscode,amount from Bonus");
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
			return listOfBonus;
		}
		else{
			for(int i=0;i<rows.size();i++){
				Bonus b = new Bonus((String) rows.get(i).get("bonuscode"), (Integer)rows.get(i).get("amount"));
				listOfBonus.add(b);
			}
		}
		return listOfBonus;
	}

	@Override
	public void assignBonus(List<String> names, String bcode) {
		String q = "update Driver set bonuscode = ? where email in (";
		for(int i=0;i<names.size();i++){
			q = q + "?,";
		}
		q = q.substring(0, q.length()-1);
		q = q+")";
		prepareQuery.setQuery(q);
		prepareQuery.substitue(bcode);
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
