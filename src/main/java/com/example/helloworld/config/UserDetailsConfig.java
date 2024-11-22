package com.example.helloworld.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserDetailsConfig {

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        // Use the PasswordEncoder to encode the password
        UserDetails user = User.withUsername("user")
                .password(passwordEncoder.encode("password")) // Correctly encode the password
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}
