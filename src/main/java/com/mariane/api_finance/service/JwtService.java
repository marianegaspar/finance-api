package com.mariane.api_finance.service;

import com.mariane.api_finance.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    private final String SECRET_KEY = "minha-chave-super-secreta-para-jwt-2026-api-finance";

    // 🔐 GERA TOKEN
    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1h
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // 🔍 EXTRAI EMAIL
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    // 🔍 VALIDA TOKEN
    public boolean isTokenValid(String token, User user) {
        final String email = extractUsername(token);
        return (email.equals(user.getEmail()) && !isTokenExpired(token));
    }

    // 🔍 EXPIRAÇÃO
    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    // 🔍 LEITURA DO TOKEN
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey()) // 🔥 AQUI está o setSigningKey
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // 🔑 CHAVE
    private Key getSignKey() {
        byte[] keyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

