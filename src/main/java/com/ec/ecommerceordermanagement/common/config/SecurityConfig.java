package com.ec.ecommerceordermanagement.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configures security rules for the application's REST APIs
 */
@Configuration
public class SecurityConfig {

    /**
     * Configures all API endpoints to allow requests without authentication
     *
     * <p>CSRF protection is disabled because we are currently testing stateless
     * REST APIs without authentication</p>
     *
     * @param http the HTTP security configuration
     * @return the configured security configuration
     * @throws Exception if security config fails
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(AbstractHttpConfigurer::disable)
                // Authentication will be introduced later
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                .build();
    }

    /**
     * Provides a BCrypt password encoder for securely hashing user passwords
     *
     * @return configured password encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
