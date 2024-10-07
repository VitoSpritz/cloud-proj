package com.example.dbcon.dbcon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        // Puoi registrare moduli personalizzati o configurare qui Jackson
        SimpleModule module = new SimpleModule();
        mapper.registerModule(module);
        return mapper;
    }
}
