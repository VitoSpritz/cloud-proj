package com.example.dbcon.dbcon.config;

import java.util.*;
import org.apache.commons.io.input.ObservableInputStream;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsProperties.System;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.time.*;

@Service
public class JwtService {

    private long jwtExpiration;
    private String secretKey;

    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    private String generateToken(HashMap<String, Object> claims, UserDetails userDetails){
        
        return buildToken(claims, userDetails, jwtExpiration);
    }

    private String buildToken(Map<String, Object> claims, UserDetails userDetails, long jwtExpiration){

        var authorities = userDetails.getAuthorities()
            .stream()
            .map(GrantedAuthority::getAuthority)
            .toList();

        return Jwts
            .builder()
            .setClaims(claims)
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
            .claim("authrities", authorities)
            .signWith(getSignInKey())
            .compact();
    }

    private Key getSignInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
