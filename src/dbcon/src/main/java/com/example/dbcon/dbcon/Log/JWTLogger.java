package com.example.dbcon.dbcon.Log;

import org.springframework.stereotype.Component;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@Component
public class JWTLogger extends HttpFilter {
    
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException{

        // Estrarre il token JWT dall'intestazione Authorization
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            // Estrarre il token JWT (rimuovendo il prefisso "Bearer ")
            String jwtToken = authorizationHeader.substring(7);
            // Stampare il token nella console
            System.out.println("JWT Token: " + jwtToken);
        }

        // Continuare la catena del filtro
        chain.doFilter(request, response);
    }
}
