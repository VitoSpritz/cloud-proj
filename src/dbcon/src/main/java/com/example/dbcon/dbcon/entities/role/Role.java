package com.example.dbcon.dbcon.entities.role;

import java.util.*;
import java.time.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.dbcon.dbcon.entities.user.User;

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
public class Role {
    
    private Integer id;
    private String name;

    private List<User> users;

    private LocalDateTime createdDAate;
    private LocalDateTime lastModifiedDate;
}
