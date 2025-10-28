package com.sales.saassaude.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())  // Para APIs REST
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**").permitAll()   // Login público
                .requestMatchers("/css/**", "/js/**").permitAll()  // Arquivos estáticos
                .anyRequest().authenticated()              // Restante precisa de login
            );
        
        return http.build();
    }
}