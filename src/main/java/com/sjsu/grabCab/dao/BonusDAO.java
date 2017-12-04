package com.sjsu.grabCab.dao;

import java.util.List;

import com.sjsu.grabCab.entity.Bonus;

public interface BonusDAO {
	boolean addBonus(String bonuscode, int amount, String string);

	List<Bonus> getBonuses();

	void assignBonus(List<String> names, String bcode);
}
