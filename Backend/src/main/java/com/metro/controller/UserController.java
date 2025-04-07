package com.metro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metro.dto.LoginRequest;
import com.metro.dto.LoginResponse;
import com.metro.dto.UserResponseDTO;
import com.metro.model.User;
import com.metro.service.JwtService;
import com.metro.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController
{

	@Autowired
	private UserService userService;
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	
	@PostMapping("/register")
	public ResponseEntity<UserResponseDTO> registerUser(@RequestBody User user){
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User savedUser = userService.registerUser(user);
		
		UserResponseDTO responseDTO = new UserResponseDTO(
				savedUser.getId(),
		        savedUser.getName(),
		        savedUser.getEmail(),
		        savedUser.getWalletBalance(),
		        savedUser.getRole()
				);
		return ResponseEntity.ok(responseDTO);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest){
		User user = userService.getUserByEmail(loginRequest.getEmail());
		
		if(user == null || !passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
		    return ResponseEntity.status(401).body("Invalid Email or Password");
		}

		String token = jwtService.generateToken(user.getEmail(), user.getRole().name());
		return ResponseEntity.ok(new LoginResponse(token));
	}
	
	@GetMapping("/profile")
	public ResponseEntity<String> getProfile(HttpServletRequest request){
		String email = (String) request.getAttribute("email");
		return ResponseEntity.ok("Welcome, " + email+ "! You accessed a protected endpoint" );
	}
}
