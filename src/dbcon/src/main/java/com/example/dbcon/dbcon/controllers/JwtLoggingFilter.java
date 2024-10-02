package com.example.dbcon.dbcon.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtLoggingFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtLoggingFilter.class);

    @Override
    protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request,
            jakarta.servlet.http.HttpServletResponse response, jakarta.servlet.FilterChain filterChain)
            throws jakarta.servlet.ServletException, IOException {
                String authorizationHeader = request.getHeader("Authorization");
        
                if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                    String token = authorizationHeader.substring(7); // Rimuovi "Bearer " dal token
        
                    // Logga il token JWT
                    logger.info("TO REMOVE: Received JWT Token: {}", token);
                }
        
                // Continua con la catena di filtri
                filterChain.doFilter(request, response);
    }
}
