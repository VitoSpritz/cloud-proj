package com.example.dbcon.dbcon.controllers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.dbcon.dbcon.JwTConverter.JwtAuthConverter;

import java.io.IOException;

public class JwtLoggingFilter extends OncePerRequestFilter {


    private static final Logger logger = LoggerFactory.getLogger(JwtAuthConverter.class);

    @Override
    protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request,
            jakarta.servlet.http.HttpServletResponse response, jakarta.servlet.FilterChain filterChain)
            throws jakarta.servlet.ServletException, IOException {
                String authorizationHeader = request.getHeader("Authorization");
        
                if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                    String token = authorizationHeader.substring(7);
                    logger.info("Token printed = " + token);
                }
                filterChain.doFilter(request, response);
    }
}
