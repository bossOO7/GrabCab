package com.sjsu.grabCab.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sjsu.grabCab.dao.AdminDAO;
import com.sjsu.grabCab.dao.BonusDAO;
import com.sjsu.grabCab.entity.Admin;

@Controller
public class BonusController {

	@Autowired
	BonusDAO bonusDAO;
	
	@Autowired
	AdminDAO adminDAO;
	
	@RequestMapping(value="/bonus", method = RequestMethod.POST)
	public ResponseEntity addPromo(Principal principal,@RequestParam("bonuscode") String bonuscode, 
			@RequestParam("amount") int amount){
		
		System.out.println(principal.getName()+"principal print");
		Admin admin = adminDAO.getAdmin(principal.getName());
		if(bonusDAO.addBonus( bonuscode, amount,admin.getEmail()))
			return ResponseEntity.ok(null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

	}
	
	@RequestMapping(value="/bonus", method = RequestMethod.GET)
	public ResponseEntity getBonus(){
		
		return ResponseEntity.status(HttpStatus.OK).body(bonusDAO.getBonuses());

	}
	
	@RequestMapping(value="/assignbonus", method = RequestMethod.POST)
	public ResponseEntity getPromos(@RequestParam("names") List<String> names,
			@RequestParam("bcode") String bcode){
		
		System.out.println(names+" "+bcode);
		bonusDAO.assignBonus(names,bcode);
		return ResponseEntity.status(HttpStatus.OK).body(null);

	}
}
