package com.mariane.api_finance.service;

import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.util.Date;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {

    private final String SECRET_KEY = "minha-chave-super-secreta-para-jwt-2026-api-finance";

    public String generateToken(String email) {

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .compact();
    }
}