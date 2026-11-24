package com.gyneobs.gyneobs_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.gyneobs.gyneobs_backend.repositories.UserRepository;


@Configuration 
public class ApplicationConfiguration {
    private final UserRepository userRepository;

public ApplicationConfiguration (UserRepository userRepository) {
    this.userRepository = userRepository;
}

// interface utilisée pour récupérer les détails des utilisateurs 
@Bean 
UserDetailsService userDetailsService() {
    return email -> userRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));
}

// applique l'algorithme de hachage pour sécuriser les mots de passe
@Bean
BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}

@Bean
AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager(); 
}

// DaoAuthenticationProvider est responsable de l'authentification des utilisateurs via userDetailsService
@Bean
public AuthenticationProvider authenticationProvider() {

    DaoAuthenticationProvider authProvider =
            new DaoAuthenticationProvider(userDetailsService());
    
    authProvider.setPasswordEncoder(passwordEncoder());

    return authProvider;
}





    

}
