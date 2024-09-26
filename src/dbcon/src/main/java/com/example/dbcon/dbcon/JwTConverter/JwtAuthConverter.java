package com.example.dbcon.dbcon.JwTConverter;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

@Component
public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken>{

    @Override
    public AbstractAuthenticationToken convert(@NonNull Jwt jwt) {
        
        return new JwtAuthenticationToken(
            jwt,
            Stream.concat(
                new JwtGrantedAuthoritiesConverter().convert(jwt).stream(),
                extractresourceRoles(jwt).stream()
            ).collect(Collectors.toSet())
        );
    }

    private Collection<? extends GrantedAuthority> extractresourceRoles(Jwt jwt){
    
        var resourceAccess = new HashMap<>(jwt.getClaim("resource_access"));

        var eternal = (Map<String, List<String>>) resourceAccess.get("account");

        var roles = eternal.get("roles");

        return roles
            .stream()
            .map(role -> new SimpleGrantedAuthority("ROLE_" + role.replace("-", "_")))
            .collect(Collectors.toSet());
    }
}


