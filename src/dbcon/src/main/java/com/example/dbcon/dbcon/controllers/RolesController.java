package com.example.dbcon.dbcon.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dbcon.dbcon.Services.Interfaces.RoleService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RolesController {
    
    private final RoleService roleService;

    @PutMapping("/assing/users/{userId}")
    public ResponseEntity<?> name(@PathVariable String userId,@RequestParam String roleName) {

        roleService.assignRole(userId, roleName);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
