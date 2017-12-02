package com.sjsu.grabCab.dao;

import org.springframework.stereotype.Component;

@Component
public class PrepareQuery {
	
	private String query;
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public PrepareQuery() {
		
	}
	PrepareQuery(String query){
		this.query = query;
	}
	void substitue(String data){
		int index = query.indexOf('?');
		if(index == -1){
			System.out.println("Exception");
		}
		String part1 = query.substring(0, index);
		String part2 = query.substring(index+1);
		query = part1+"\""+data+"\""+part2;
	}
	void substitue(int data){
		int index = query.indexOf('?');
		if(index == -1){
			System.out.println("Exception");
		}
		String part1 = query.substring(0, index);
		String part2 = query.substring(index+1);
		query = part1+data+part2;
	}
	void substitue(long data){
		int index = query.indexOf('?');
		if(index == -1){
			System.out.println("Exception");
		}
		String part1 = query.substring(0, index);
		String part2 = query.substring(index+1);
		query = part1+data+part2;
	}
	
}
