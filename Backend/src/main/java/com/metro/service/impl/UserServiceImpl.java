package com.metro.service.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metro.model.Role;
import com.metro.model.User;
import com.metro.repository.UserRepository;
import com.metro.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User registerUser(User user) {
		user.setId( UUID.randomUUID().toString());
		user.setRole(Role.USER);
		user.setWalletBalance(0.0);
		return userRepository.save(user);
	}
	
	@Override
	public User getUserByEmail(String email) {
		Optional<User> optionalUser = userRepository.findByEmail(email);
		return optionalUser.orElse(null);
	}
	
}
