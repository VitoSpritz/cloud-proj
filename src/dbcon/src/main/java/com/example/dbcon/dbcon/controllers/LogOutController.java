package com.example.dbcon.dbcon.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class LogOutController {
    
    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        request.getSession().invalidate();

        String redirectUri = "http://localhost:8080/";
        String keycloakLogoutUrl = "http://localhost:9180/realms/Prova/protocol/openid-connect/logout";

        response.sendRedirect(keycloakLogoutUrl);
    }
}
