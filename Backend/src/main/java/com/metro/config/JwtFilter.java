package com.metro.config;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Collections;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.IOException;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Component
public class JwtFilter extends OncePerRequestFilter
{
	@Value("${jwt.secret}")
    private String secret_key;
	
	@Override
	protected void doFilterInternal(
			HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain) throws ServletException, IOException, java.io.IOException{
		
	    // ⛔ Skip filter for register/login endpoints
	    String path = request.getRequestURI();
	    if (path.contains("/register") || path.contains("/login")) {
	        filterChain.doFilter(request, response);
	        return;
	    }
		String authHeader = request.getHeader("Authorization");
		if(authHeader != null && authHeader.startsWith("Bearer ")) {
			String token = authHeader.substring(7);
			
			try {
				Key key = Keys.hmacShaKeyFor(secret_key.getBytes(StandardCharsets.UTF_8));
				Claims claims = Jwts
						.parserBuilder()
						.setSigningKey(key)
						.build()
						.parseClaimsJws(token)
						.getBody();
				request.setAttribute("email", claims.getSubject());
				
				String role = (String) claims.get("role");
				List<GrantedAuthority> authorities = Collections.singletonList(
					    new SimpleGrantedAuthority("ROLE_" + role)
					);
				// ✅ Set Spring Security context
			    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
			        claims.getSubject(), null, authorities);
			    SecurityContextHolder.getContext().setAuthentication(authToken);
			    System.out.println("AuthToken Set: " + SecurityContextHolder.getContext().getAuthentication());

			} catch (Exception e) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				response.getWriter().write("Invalid or expird Token");
				return;
			}
		} else {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter().write("Missing Token");
			return;
		}
		
		filterChain.doFilter(request, response);
	}
}
