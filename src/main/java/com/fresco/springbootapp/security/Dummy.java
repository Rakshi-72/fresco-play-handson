package com.fresco.springbootapp.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
public class Dummy {

    private final String secrete = "Rakshith";

    private <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .setSigningKey(secrete)
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    public Boolean isTokenValid(String token, UserDetails details) {
        String username = extractUsername(token);
        return username.equals(details.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractClaims(token, Claims::getExpiration).before(new Date());
    }

    public String generateToken(UserDetails details) {
        return Jwts
                .builder()
                .setIssuer("tcs")
                .setAudience("all - users")
                .setClaims(getClaims(details))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 5))
                .setSubject(details.getUsername())
                .signWith(SignatureAlgorithm.HS256, secrete)
                .compact();
    }

    private Claims getClaims(UserDetails details) {

        Claims claims = Jwts.claims().setSubject(details.getUsername());
//        List<SimpleGrantedAuthority> authorities = details.getAuthorities()
//                .stream()
//                .map(auth -> new SimpleGrantedAuthority(auth.getAuthority()))
//                .toList();
        claims.put("auth", details.getAuthorities());
        return claims;
    }


}
