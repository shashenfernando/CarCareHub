package com.example.carcarehub.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
            .formLogin(httpForm ->{
                httpForm
                        .loginPage("/api/v1/login/userLogin").permitAll();
            })
            .authorizeHttpRequests(registry ->{
                registry.requestMatchers("/api/v1/user/createUser").permitAll();
                registry.anyRequest().authenticated();
            })
            .build();
}
}
