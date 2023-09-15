package com.example.demo.oneStopShop.securityConfig;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtTokenGenerator extends OncePerRequestFilter {
	
	private String token = null;
	
	public String receiveToken() {
		return this.token;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication != null) {
			SecretKey key = Keys.hmacShaKeyFor(SecurityContext.JWT_HEADER.getBytes());
			
			String jwt = Jwts.builder().setIssuer("self")
					.setIssuedAt(new Date())
					.claim("authorities", populateAuthorities(authentication.getAuthorities()))
					.claim("username",authentication.getName())
					.setExpiration(new Date(new Date().getTime() + 300000000))
					.signWith(key).compact();
			
			response.setHeader(SecurityContext.HEADER, jwt);
		}
		
		this.token = response.getHeader("Authorization");
		
		filterChain.doFilter(request, response);
		
	}
	
	public String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
		Set<String> authorities = new HashSet<>();
		
		for(GrantedAuthority authority:collection) {
			authorities.add(authority.getAuthority());
		}
		
		return String.join(",", authorities);
	}

	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return !request.getServletPath().equals("/signIn");
	}

}
