package com.example.dbcon.dbcon.entities.user;

import java.util.*;
import java.util.stream.Collectors;
import java.security.Principal;
import java.time.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.dbcon.dbcon.entities.role.Role;

import jakarta.persistence.EntityListeners;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User implements UserDetails, Principal{

    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private LocalDate dateOfBirth;
    private String password;
    private boolean accountLocked;
    private boolean enable;

    private LocalDateTime createdDAate;
    private LocalDateTime lastModifiedDate;

    private List<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles
        .stream()
        .map(r -> new SimpleGrantedAuthority(r.getName()))
        .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return firstname;
    }

    @Override
    public String getName() {
        return email;
    }

    private String fullName(){
        return firstname + " " + lastname;
    }
    
}
