package com.teste.teste.aplicacao.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	private static final SecretKey SECRET_KEY = Keys
			.hmacShaKeyFor("chave-muito-secreta-com-no-minimo-32-caracteres!".getBytes());

//	private static final long ACCESS_EXPIRATION = 1000 * 60 * 10; // 10 min
	private static final long ACCESS_EXPIRATION = 1000L * 60 * 60 * 24 * 14; // 14 dias

	public static String generateAccessToken(String username) {
		return Jwts.builder().setSubject(username).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + ACCESS_EXPIRATION))
				.signWith(SECRET_KEY, SignatureAlgorithm.HS256).compact();
	}

	public static String generateRefreshToken(String username) {
		return Jwts.builder().setSubject(username).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + ACCESS_EXPIRATION))
				.signWith(SECRET_KEY, SignatureAlgorithm.HS256).compact();
	}

	public static boolean isTokenValid(String token) {
		return getExpiration(token).after(new Date());
	}

	public static String extractUsername(String token) {
		return getClaims(token).getSubject();
	}

	private static Date getExpiration(String token) {
		return getClaims(token).getExpiration();
	}

	private static Claims getClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();
	}
}