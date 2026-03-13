package com.autoever.recall.auth.config;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

public interface SecurityConfig {
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception;

    BCryptPasswordEncoder passwordEncoder();

    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration);
}
