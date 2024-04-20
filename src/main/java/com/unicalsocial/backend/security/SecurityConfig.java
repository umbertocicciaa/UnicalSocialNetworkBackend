package com.unicalsocial.backend.security;

import com.unicalsocial.backend.exception.SecurityErrorHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthConverter jwtAuthConverter;
    private final SecurityErrorHandler securityErrorHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.sessionManagement(session -> session.sessionCreationPolicy(STATELESS));
        http.exceptionHandling(exp -> exp.authenticationEntryPoint(securityErrorHandler));
        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
        http.oauth2ResourceServer(oauth -> oauth.jwt(jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(jwtAuthConverter)));
        return http.build();
    }
}
