package com.sjsu.grabCab.entity;

import javax.persistence.Entity;

@Entity
public class Promo {
	String promocode;
	int discountPrice;
	public Promo(String promocode, int discountPrice) {
		super();
		this.promocode = promocode;
		this.discountPrice = discountPrice;
	}
	public Promo(String promocode, int discountPrice, String email) {
		super();
		this.promocode = promocode;
		this.discountPrice = discountPrice;
		this.email = email;
	}
	public String getPromocode() {
		return promocode;
	}
	public void setPromocode(String promocode) {
		this.promocode = promocode;
	}
	public int getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(int discountPrice) {
		this.discountPrice = discountPrice;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	String email;
	
}
