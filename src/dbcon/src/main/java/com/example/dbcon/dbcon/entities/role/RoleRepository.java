package com.example.dbcon.dbcon.entities.role;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    
    Optional<Role> findByName(String role);
}
