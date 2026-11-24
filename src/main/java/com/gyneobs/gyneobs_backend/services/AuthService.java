package com.gyneobs.gyneobs_backend.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gyneobs.gyneobs_backend.entities.User;
import com.gyneobs.gyneobs_backend.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User loginUser(String email, String password) {

        return userRepository.findByEmail(email)
                .filter(user -> passwordEncoder.matches(
                        password,
                        user.getPassword()
                ))
                .orElse(null);
    }

    public User logoutUser(String email) {
        return userRepository.findByEmail(email)
                .orElse(null);
    }
}