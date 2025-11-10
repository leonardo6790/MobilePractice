package com.app.backend.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.secirity.keys;
import org.springframework.beans.factory.annotation.value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import javax.crypto.Secretkey;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value ("${jwt.secret}")
    private String jwtSecret;

    @Value ("${jwt.expiration}")
    private long jwtExpiration;

    private SecretKey getSigningKey(){
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    public String generateToken(Authentication authentication){
        String username = authentication.getName();
        Date now = new Date();
        Date expiryDate =  new Date(now.getTime() + jwtExpiration);

        return Jwts.builder()
        .subject(username)
        .issuedAt(now)
        .expiration(expiryDate())
        .signWith(getSigningKey())
        .compact();
    }

    public String getUsernameFromToken (String token) {
        Claims claims = Jwts.parser()
        .verifyWith(getSigningKey())
        .build()
        .parseSignedClaims(token)
        .getPayload()

        return claims.getSubject();
    }

    public boolean validateToken(String authToken){
        try{
            Jwts.parser()
            .verifyWith(getSigningKey())
            .build()
            .parseSignedClaims(authToken);
            return true;
        } catch (jwtExceptions | IllegalArgumentExeption e){
            return false;
        }
    }
}
