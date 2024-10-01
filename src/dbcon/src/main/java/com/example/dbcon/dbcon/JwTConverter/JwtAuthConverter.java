package com.example.dbcon.dbcon.JwTConverter;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthConverter.class);

    @Override
    public AbstractAuthenticationToken convert(@NonNull Jwt source) {
        logger.info("Converting JWT to Authentication Token...");

        // Log the entire JWT for debugging
        logger.debug("JWT Claims: {}", source.getClaims());

        Collection<GrantedAuthority> authorities = Stream.concat(
                new JwtGrantedAuthoritiesConverter().convert(source).stream(), // ruoli
                Stream.concat(
                    extractResourceRoles(source).stream(), // ruoli da resource_access
                    extractUserGroups(source).stream() // gruppi
                )
        ).collect(Collectors.toSet());

        logger.info("Authorities extracted: {}", authorities);
        
        return new JwtAuthenticationToken(source, authorities);
    }

    private Collection<? extends GrantedAuthority> extractResourceRoles(Jwt jwt) {
        logger.info("Extracting resource roles from JWT...");
        var resourceAccess = new HashMap<>(jwt.getClaim("resource_access"));
        logger.debug("Resource Access Claim: {}", resourceAccess);
        var eternal = (Map<String, List<String>>) resourceAccess.get("applicazione");
        

        if(eternal == null){
            logger.warn("No resource roles found for 'applicazione'. Returning empty authorities.");
            return Collections.emptySet();
        }

        var roles = eternal.get("roles");
        logger.debug("Roles extracted: {}", roles);
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.replace("-", "_")))
                .collect(Collectors.toSet());
    }

    private Collection<? extends GrantedAuthority> extractUserGroups(Jwt jwt) {
        logger.info("Extracting user groups from JWT...");

        // Assumendo che "user_group_jwt" sia una lista di gruppi
        var userGroups = jwt.getClaim("user_group_jwt");

        if (userGroups instanceof List<?>) {
            logger.info("Sesso ananle" + ((List<?>) userGroups).stream()
            .map(group -> new SimpleGrantedAuthority("GROUP_" + group.toString().replace("-", "_")))
            .collect(Collectors.toSet()).toString());
            return ((List<?>) userGroups).stream()
                    .map(group -> new SimpleGrantedAuthority("GROUP_" + group.toString().replace("-", "_")))
                    .collect(Collectors.toSet());
        }

        logger.warn("user_group_jwt claim is not a List. Returning empty authorities.");
        return Collections.emptySet();
    }
}



