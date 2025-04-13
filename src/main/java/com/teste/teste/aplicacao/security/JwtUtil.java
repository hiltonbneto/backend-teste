package com.teste.teste.aplicacao.security;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	private static final String SECRET_KEY = "seuSegredoUltraSecretoAqui";
	private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10 horas

	public static String generateToken(String username) {
		return Jwts.builder().setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}

	public static String extractUsername(String token) {
		return getClaims(token).getSubject();
	}

	public static boolean isTokenValid(String token) {
		return !getClaims(token).getExpiration().before(new Date());
	}

	private static Claims getClaims(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}
}