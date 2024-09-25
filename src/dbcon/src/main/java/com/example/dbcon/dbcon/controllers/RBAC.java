package com.example.dbcon.dbcon.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/rbac")
@RequiredArgsConstructor
public class RBAC {
    
    @GetMapping("role")
    @PreAuthorize("harRole('<someRole>')")
    public ResponseEntity<?> testForRole(){



        return null;
    }
}
