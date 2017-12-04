package com.sjsu.grabCab.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sjsu.grabCab.dao.AdminDAO;
import com.sjsu.grabCab.dao.DriverDAO;
import com.sjsu.grabCab.dao.PromoDAO;
import com.sjsu.grabCab.entity.Admin;

@Controller
public class PromoController {

	@Autowired
	PromoDAO promoDAO;
	
	@Autowired
	AdminDAO adminDAO;
	
	@RequestMapping(value="/promo", method = RequestMethod.POST)
	public ResponseEntity addPromo(Principal principal,@RequestParam("promocode") String promocode, 
			@RequestParam("discountprice") int discountprice){
		
		System.out.println(principal.getName()+"principal print");
		Admin admin = adminDAO.getAdmin(principal.getName());
		if(promoDAO.addPromo( promocode, discountprice,admin.getEmail()))
			return ResponseEntity.ok(null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

	}

}
