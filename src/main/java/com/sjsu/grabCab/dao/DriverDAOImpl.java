package com.sjsu.grabCab.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sjsu.grabCab.entity.Driver;
import com.sjsu.grabCab.entity.Passenger;

@Transactional
@Repository
public class DriverDAOImpl implements DriverDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public String addUser(String username, String password, String email) {
		Session session = sessionFactory.getCurrentSession();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String p = passwordEncoder.encode(password);
		Driver user = new Driver(username,p, email);
		//Passenger user = new Passenger(username,password);
		session.save(user);
		return null;
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		System.out.println("from dao impl"+username);
		Session session = sessionFactory.getCurrentSession();
		System.out.println(username);
		Query query = session.createQuery("from Driver where username = :username or email = :username ");
		query.setParameter("username", username);
		List list = query.list();
		System.out.println(list.isEmpty());
		if(list.size()==0){
			//throw new Exception("No user found with that username");
		}
		Driver d = (Driver) list.get(0);
		System.out.println(d.getUsername());
		return (UserDetails) list.get(0);
	}

	@Override
	public Driver getDriver(String username) {
		Session session = sessionFactory.getCurrentSession();
		System.out.println(username);
		Query query = session.createQuery("from Driver where username = :username or email = :username ");
		query.setParameter("username", username);
		List list = query.list();
		System.out.println(list.isEmpty());
		if(list.size()==0){
			//throw new Exception("No user found with that username");
		}
		Driver d = (Driver) list.get(0);
		System.out.println(d.getUsername());
		return d;
	}

}
