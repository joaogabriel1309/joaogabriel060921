package com.seplag.musicapi.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    private static final long CNT_EXPIRACAO = 1000 * 60 * 5;

    private static final String CNT_SECRET = "chave-super-secreta-para-o-desafio-seplag-2026";

    private Key getKey() {
        return Keys.hmacShaKeyFor(CNT_SECRET.getBytes());
    }

    public String gerarToken(String name) {
        return Jwts.builder()
                .setSubject(name)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + CNT_EXPIRACAO))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String getUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean isTokenValido(String token, UserDetails user) {
        String username = getUsername(token);
        return username.equals(user.getUsername()) && !isExpirado(token);
    }

    private boolean isExpirado(String token) {
        Date exp = Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();

        return exp.before(new Date());
    }
}
