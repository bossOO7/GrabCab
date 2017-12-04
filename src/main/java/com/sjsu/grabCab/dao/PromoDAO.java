package com.sjsu.grabCab.dao;

import java.util.List;

import com.sjsu.grabCab.entity.Promo;

public interface PromoDAO {

	boolean addPromo(String promocode, int discountprice, String string);

	List<Promo> getPromos();

	void assignPromos(List<String> names, String pcode);
}
