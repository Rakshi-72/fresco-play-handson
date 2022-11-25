package com.fresco.springbootapp.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtils {

    private final String secrete = "Rakshith";

    /**
     * It creates a JWT token with the claims from the user, sets the issuer,
     * audience, subject, issued at
     * and expiration date, signs it with the secret and returns the compact version
     * of the token
     *
     * @param principal The user object that contains the user's information.
     * @return A JWT token
     */
    public String generateToken(UserDetails principal) {
        // Map<String, Object> claims = new HashMap<>();
        Claims claims = getClaimsFromUser(principal);
        return Jwts
                .builder()
                .setClaims(claims)
                .setIssuer("rakshi")
                .setAudience("those who use this app")
                .setSubject(principal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 5))
                .signWith(SignatureAlgorithm.HS256, secrete)
                .compact();
    }

    /**
     * It creates a claims object from the user details.
     *
     * @param principal The user object that contains the user's information.
     * @return A JWT token
     */
    private Claims getClaimsFromUser(UserDetails principal) {
        // List<SimpleGrantedAuthority> auth = principal.getAuthorities()
        // .stream()
        // .map(s -> new SimpleGrantedAuthority(s.getAuthority()))
        // .toList();

        Claims claims = Jwts.claims().setSubject(principal.getUsername());
        claims.put("authorities", principal.getAuthorities());
        return claims;
    }

    /**
     * > If the username in the token matches the username in the user details and
     * the token is not
     * expired, then the token is valid
     *
     * @param token   The token to validate
     * @param details The user details object that contains the user's credentials.
     * @return A boolean value.
     */
    public Boolean isTokenValid(String token, UserDetails details) {

        String username = getUsername(token);
        return username.equals(details.getUsername()) && isTokenExpired(token);

    }

    /**
     * ExtractClaims is a function that takes a token and a function that takes a Claims object and returns a String, and
     * returns a String.
     *
     * @param token The JWT token
     * @return The username of the user who is logged in.
     */
    public String getUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    /**
     * If the expiration date of the token is before the current date, then the
     * token is expired
     *
     * @param token The JWT token to be validated.
     * @return A boolean value.
     */
    private boolean isTokenExpired(String token) {
        return !extractClaims(token, Claims::getExpiration).before(new Date());
    }

    /**
     * It takes a token and a function as input, extracts the claims from the token,
     * and then applies
     * the function to the claims
     *
     * @param token          The JWT token
     * @param claimsResolver A function that takes in a Claims object and returns a
     *                       value of type T.
     * @return A Claims object
     */
    private <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * It takes a token and returns a Claims object
     *
     * @param token The token that needs to be validated.
     * @return The claims are being returned.
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secrete).parseClaimsJws(token).getBody();
    }

}