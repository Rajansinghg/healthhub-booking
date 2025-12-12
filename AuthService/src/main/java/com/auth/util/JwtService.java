package com.auth.util;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.auth.payload.dto.UserDTO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {

	private final String SECRET = "rajankumarnishantkumarmonakumarinishansinghviveksinghRAJANKUMARNISHANTKUMARMONAKUMARINISHASINGHVIVEKSINGH";
	
	public String generateToken(UserDTO user) {
		return Jwts.builder()
				.setSubject(user.getEmail())
				.claim("role", user.getRole())
				.claim("id", user.getId())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*60*24))
				.signWith(SignatureAlgorithm.HS256,SECRET)
				.compact();
	}
	
	public Claims validate(String token) {
		return Jwts.parser()
				.setSigningKey(SECRET)
				.parseClaimsJws(token)
				.getBody();
	}
}
