package com.example.dbcon.dbcon.controllers;

import org.apache.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dbcon.dbcon.Services.UserService;
import com.example.dbcon.dbcon.entities.NewUser;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody NewUser newUser){
        userService.createUser(newUser);
        return ResponseEntity.status(HttpStatus.SC_CREATED).build();

    }

    @PutMapping("/{id}/verifyEmail")
    public ResponseEntity<?> sendVerificationEmail(@PathVariable String id){
        
        userService.sendVerificationEmail(id);
        return ResponseEntity.status(HttpStatus.SC_OK).build();

    }
}
