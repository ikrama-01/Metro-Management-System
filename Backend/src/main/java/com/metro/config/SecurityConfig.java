package com.metro.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private JwtFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
	        .csrf(csrf -> csrf.disable())
	        .authorizeHttpRequests(auth -> auth
	        		.requestMatchers("/api/stations/add", "/api/stations/update/**", "/api/stations/delete/**").hasRole("ADMIN")
	        		.requestMatchers("/api/routes/add", "/api/routes/update/**", "/api/routes/delete/**").hasRole("ADMIN")
	        		.requestMatchers("api/tickets/all").hasRole("ADMIN")
	        		
	        		.requestMatchers("/api/stations/all", "/api/stations/**").authenticated()
	        		.requestMatchers("/api/routes/all", "/api/routes/**").authenticated()
	        		.requestMatchers("/api/wallet/**").authenticated()
	        	    .requestMatchers("/api/users/profile").authenticated()
	        	    .requestMatchers("/api/tickets/**").authenticated() 
	        		.requestMatchers("/auth/**","/api/users/register", "/api/users/login").permitAll()
	        	    .anyRequest().authenticated()
	        	)
	        .sessionManagement(session -> session
	            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        )
	        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
	        .build();
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    



}
