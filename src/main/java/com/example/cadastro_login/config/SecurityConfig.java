package com.example.cadastro_login.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // Permite configurar as regras de segurança HTTP
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // desativa CSRF para facilitar testes (não recomendado para produção)
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/api/usuarios/cadastro", "/api/usuarios/login").permitAll() // libera cadastro e login
                .anyRequest().authenticated() // protege as outras rotas
            )
            .httpBasic(); // autenticação básica HTTP (pode mudar para JWT depois)

        return http.build();
    }

    // Bean para criptografar senhas
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Bean para AuthenticationManager (necessário se for autenticar manualmente)
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}

