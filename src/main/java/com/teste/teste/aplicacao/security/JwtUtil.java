package com.teste.teste.aplicacao.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	private static final SecretKey SECRET_KEY = Keys
			.hmacShaKeyFor("sua-chave-secreta-com-no-mínimo-32-caracteres!".getBytes());

	private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10 horas

	public static String generateToken(String username) {
		return Jwts.builder().setSubject(username).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SECRET_KEY, SignatureAlgorithm.HS256).compact();
	}

	public static String extractUsername(String token) {
		return Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody().getSubject();
	}

	public static boolean isTokenValid(String token) {
		Date expiration = Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody()
				.getExpiration();
		return expiration.after(new Date());
	}
}