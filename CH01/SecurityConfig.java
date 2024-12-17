package com.springmvctutorial.springboot_springmvc_first_app.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .headers(headers -> headers
                        .httpStrictTransportSecurity(hsts ->
                                hsts.maxAgeInSeconds(31536000).includeSubDomains(true)) // Enforce HTTPS
                        .contentSecurityPolicy(csp ->
                                csp.policyDirectives("script-src 'self'; object-src 'none'")) // Add CSP
                        .frameOptions(frameOptions -> frameOptions.deny()) // Prevent clickjacking
                )
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // Adjust as needed
                );

        return http.build();
    }
}





























