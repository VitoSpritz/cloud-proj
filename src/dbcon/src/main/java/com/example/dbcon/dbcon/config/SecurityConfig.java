package com.example.dbcon.dbcon.config;

// import org.keycloak.authorization.client.util.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.example.dbcon.dbcon.JwTConverter.JwtAuthConverter;
import com.example.dbcon.dbcon.Log.JWTLogger;

import lombok.RequiredArgsConstructor;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {


    /*
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/").permitAll();
                    auth.anyRequest().authenticated();
                })
                .oauth2Login(oauth2 -> oauth2
                                .loginPage("/oauth2/authorization/keycloak")
                                .defaultSuccessUrl("/", true)
                )
                .oauth2ResourceServer(server -> server.jwt(Customizer.withDefaults()));

        http.addFilterBefore(jwtLoggingFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    */

    /*
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http
        .csrf((csrf) -> csrf.disable())
        .authorizeHttpRequests((auth) -> {
            auth.requestMatchers("/").permitAll();
            //auth.anyRequest().authenticated();
        });

        http
            .oauth2Login(ouauth2 -> ouauth2
                .loginPage("/oauth2/authorization/keycloak")
                .defaultSuccessUrl("/", true));

        http
        .oauth2ResourceServer(oauth2 -> oauth2
            .jwt(jwt -> jwt
                .jwtAuthenticationConverter(jwtAuthConverter)
            )
        );

        http
            .sessionManagement(management -> management
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }*/

    private final JwtAuthConverter jwtAuthConverter;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){

        return (web) -> {
            web.ignoring().requestMatchers(
                HttpMethod.POST,
                "/public/**",
                "/users"
            );
            web.ignoring().requestMatchers(

                HttpMethod.GET,
                "/public/**"
            );
            web.ignoring().requestMatchers(
                HttpMethod.DELETE,
                "/public/**",
                "/users/delete/{id}"
            );
            web.ignoring().requestMatchers(
                HttpMethod.PUT,
                "/public/**"
            );
            web.ignoring().requestMatchers(
                HttpMethod.OPTIONS,
                "/**"
            )
            .requestMatchers("/configuration/**", "/swagger-ui/**");      
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

        return httpSecurity
            .authorizeHttpRequests(auth -> auth
                .anyRequest()
                .authenticated()
            )
            .oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwt -> jwt
                    .jwtAuthenticationConverter(jwtAuthConverter)
                )
            )
            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .build();
    }

}
