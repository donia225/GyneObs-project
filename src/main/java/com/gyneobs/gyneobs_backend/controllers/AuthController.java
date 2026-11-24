package com.gyneobs.gyneobs_backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gyneobs.gyneobs_backend.dto.LoginRequest;
import com.gyneobs.gyneobs_backend.dto.LoginResponse;
import com.gyneobs.gyneobs_backend.entities.User;
import com.gyneobs.gyneobs_backend.services.AuthService;
import com.gyneobs.gyneobs_backend.services.JwtService;

@RestController 
@RequestMapping("/auth")
public class AuthController {
private final AuthService authService;
private final JwtService jwtService;

public AuthController(AuthService authService, JwtService jwtService) {
    this.authService = authService;
    this.jwtService = jwtService;
}
@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody LoginRequest request) {

    User user = authService.loginUser(
        request.email(),
        request.password()
    );

    if (user != null) {
        String token = jwtService.generateToken(user);

    return ResponseEntity.ok(new LoginResponse(token));
    }

    return ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .body("Email ou mot de passe incorrect");
}

public ResponseEntity<User> logout(String email) {
    User user = authService.logoutUser(email);
    if (user != null) {
        return ResponseEntity.ok(user);
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}



}