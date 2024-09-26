package com.example.dbcon.dbcon.entities.user;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface TokenRepository extends JpaRepository<Token, Integer> {
    
    Optional<Token> findByToken(String token);
}
