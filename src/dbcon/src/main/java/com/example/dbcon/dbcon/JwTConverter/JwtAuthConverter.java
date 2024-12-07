package com.example.dbcon.dbcon.JwTConverter;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    @Override
    public AbstractAuthenticationToken convert(@NonNull Jwt source) {

        Collection<GrantedAuthority> authorities = Stream.concat(
                new JwtGrantedAuthoritiesConverter().convert(source).stream(), // ruoli
                Stream.concat(
                    extractResourceRoles(source).stream(), // ruoli da resource_access
                    extractUserGroups(source).stream() // gruppi
                )
        ).collect(Collectors.toSet());
        
        return new JwtAuthenticationToken(source, authorities);
    }

    private Collection<? extends GrantedAuthority> extractResourceRoles(Jwt jwt) {
        var resourceAccess = new HashMap<>(jwt.getClaim("resource_access"));
        var eternal = (Map<String, List<String>>) resourceAccess.get("applicazione");

        if(eternal == null){
            return Collections.emptySet();
        }

        var roles = eternal.get("roles");
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.replace("-", "_")))
                .collect(Collectors.toSet());
    }

    private Collection<? extends GrantedAuthority> extractUserGroups(Jwt jwt) {

        // Assumendo che "user_group_jwt" sia una lista di gruppi
        var userGroups = jwt.getClaim("user_group_jwt");

        if (userGroups instanceof List<?>) {
            return ((List<?>) userGroups).stream()
                    .map(group -> new SimpleGrantedAuthority("GROUP_" + group.toString().replace("-", "_")))
                    .collect(Collectors.toSet());
        }
        return Collections.emptySet();
    }
}



