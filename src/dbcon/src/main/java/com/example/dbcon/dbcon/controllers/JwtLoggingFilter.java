package com.example.dbcon.dbcon.controllers;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

public class JwtLoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request,
            jakarta.servlet.http.HttpServletResponse response, jakarta.servlet.FilterChain filterChain)
            throws jakarta.servlet.ServletException, IOException {
                String authorizationHeader = request.getHeader("Authorization");
        
                if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                }
                filterChain.doFilter(request, response);
    }
}
