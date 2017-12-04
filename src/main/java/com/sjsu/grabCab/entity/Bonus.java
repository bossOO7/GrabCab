package com.sjsu.grabCab.entity;

public class Bonus {
	String bonuscode;
	int amount;
	String email;
	public String getBonuscode() {
		return bonuscode;
	}
	public Bonus(String bonuscode, Integer integer) {
		super();
		this.bonuscode = bonuscode;
		this.amount = integer;
	}
	public Bonus(String bonuscode, int amount, String email) {
		super();
		this.bonuscode = bonuscode;
		this.amount = amount;
		this.email = email;
	}
	public void setBonuscode(String bonuscode) {
		this.bonuscode = bonuscode;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
