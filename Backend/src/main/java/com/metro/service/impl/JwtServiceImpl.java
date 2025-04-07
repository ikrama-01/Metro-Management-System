package com.metro.service.impl;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.metro.service.JwtService;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtServiceImpl implements JwtService
{
	@Value("${jwt.secret}")
	private String SECRET;
	private final long EXPIRATION = 1000 * 60 * 60 * 10;
	
	private Key getSignKey() {
		return Keys.hmacShaKeyFor(SECRET.getBytes());
	}
	
	public String generateToken(String email, String role) {
		return Jwts.builder()
				.setSubject(email)
				.claim("role", role.toUpperCase())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()+EXPIRATION))
				.signWith(getSignKey(), SignatureAlgorithm.HS256)
				.compact();
	}
	
	public String extractEmail(String token) {
		return Jwts.parserBuilder().setSigningKey(getSignKey()).build()
				.parseClaimsJws(token).getBody().getSubject();
	}
	
	public boolean validToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
			return true;
		} catch (JwtException e) {
			return false;
		}
	}
}

