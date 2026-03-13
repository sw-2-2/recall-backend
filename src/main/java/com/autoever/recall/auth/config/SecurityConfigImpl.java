package com.autoever.recall.auth.config;

import com.autoever.recall.user.domain.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@Profile("!local")
@RequiredArgsConstructor
public class SecurityConfigImpl implements SecurityConfig {
    private final AuthenticationEntryPoint authenticationEntryPoint;

    private final String[] whiteList = {
            "/api/users/signup",
            "/api/auth/login",
            "/api/schools/**",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/api-docs/**"
    };

    @Bean
    @Override
    public SecurityFilterChain filterChain(HttpSecurity http) {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.ignoringRequestMatchers(whiteList))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(whiteList).permitAll()
                        .anyRequest().hasRole(UserRole.USER.name())
                )
                .sessionManagement(session -> session
                        .sessionFixation().changeSessionId()
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(false)
                )
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(authenticationEntryPoint)
                )
                .logout(AbstractHttpConfigurer::disable);
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(
                List.of("http://localhost:3000", "https://recallhub.vercel.app")
        );
        config.setAllowedMethods(
                List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")
        );
        config.setAllowCredentials(true);
        config.setAllowedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    @Override
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) {
        return configuration.getAuthenticationManager();
    }
}
