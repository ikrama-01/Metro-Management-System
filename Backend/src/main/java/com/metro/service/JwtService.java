package com.metro.service;


public interface JwtService {
    String generateToken(String email, String role);
    String extractEmail(String token);
    boolean validToken(String token);
}
