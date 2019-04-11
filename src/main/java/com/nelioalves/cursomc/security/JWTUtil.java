package com.nelioalves.cursomc.security;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {

	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration} ")
	private String expiration;
	
	
	
	public String generateToken(String username) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.SECOND, Integer.parseInt(expiration.trim()));
		
		return Jwts.builder()
				.setSubject(username)
				.setExpiration(calendar.getTime())
				.signWith(SignatureAlgorithm.HS512, secret.getBytes())
				.compact();
	}
}
