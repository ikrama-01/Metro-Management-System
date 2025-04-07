package com.metro.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.metro.model.User;
import com.metro.repository.UserRepository;
import com.metro.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/wallet")
@CrossOrigin
public class WalletController 
{
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/balance")
	public ResponseEntity<Double> getBalance(HttpServletRequest request){
		String email = (String) request.getAttribute("email");
		User user = userService.getUserByEmail(email);
		return ResponseEntity.ok(user.getWalletBalance());
	}
	
	@PostMapping("/recharge")
	public ResponseEntity<String> rechargeWallet(HttpServletRequest request, @RequestParam Double amount){
		String email = (String) request.getAttribute("email");
		User user = userService.getUserByEmail(email);
		
		user.setWalletBalance(user.getWalletBalance() + amount);
		userRepository.save(user);
		return ResponseEntity.ok("Wallet Recharged. New Balance: " + user.getWalletBalance());
	}
}
