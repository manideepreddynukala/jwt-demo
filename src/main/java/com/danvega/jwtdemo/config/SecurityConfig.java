package com.danvega.jwtdemo.config;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


import static org.springframework.security.config.Customizer.*;
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager user() {
        return new InMemoryUserDetailsManager(
                User.withUsername("manideep")
                        .password("{noop}password")
                        .authorities("read")
                        .build()
        );
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        //Never leave csrf disabled by leaving sessionManagement enabled
        return httpSecurity.
                csrf(csrf -> csrf.disable()).
                authorizeRequests(auth-> auth.anyRequest().authenticated()).
                sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)). //since we are developing restful services, we don't need session
                        httpBasic(withDefaults()).build();
    }
}
