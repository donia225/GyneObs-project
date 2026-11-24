package com.gyneobs.gyneobs_backend.services;



import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.gyneobs.gyneobs_backend.dto.CreateSecretaryRequest;
import com.gyneobs.gyneobs_backend.dto.UserResponse;
import com.gyneobs.gyneobs_backend.entities.User;
import com.gyneobs.gyneobs_backend.enums.Role;
import com.gyneobs.gyneobs_backend.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

public UserRepository getUserRepository() {
    return userRepository;
}

public User createSecretary(User user) {
    return userRepository.save(user);
}

public UserResponse getSecretaryById(Long id) {

    User secretary = userRepository.findById(id)
            .orElseThrow(() ->
                    new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Secrétaire introuvable"
                    )
            );

    if (secretary.getRole() != Role.SECRETAIRE) {
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Secrétaire introuvable"
        );
    }

    return toUserResponse(secretary);
}

public List<UserResponse> listAllSecretaires() {
    return userRepository.findByRole(Role.SECRETAIRE)
                         .stream()
                         .map(this::toUserResponse)
                         .toList();
}
public UserResponse activateSecretary(Long id) {

    User secretary = userRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Secrétaire introuvable"
            ));

    if (secretary.getRole() != Role.SECRETAIRE) {
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Secrétaire introuvable"
        );
    }

    secretary.setActive(true);

    User savedSecretary = userRepository.save(secretary);

    return toUserResponse(savedSecretary);
}
public User deactivateUser(Long id) {
    User user = userRepository.findById(id).orElse(null);
    if (user != null) {
        // Assuming you have a field 'active' in your User entity
        user.setActive(false);
        return userRepository.save(user);
    }
    return null;
}

public User updateUser(Long id, User updatedUser) {
    User existingUser = userRepository.findById(id).orElse(null);
    if (existingUser != null) {
        existingUser.setLastname(updatedUser.getLastname());
        existingUser.setFirstname(updatedUser.getFirstname());
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());
      
        return userRepository.save(existingUser);
    }
    return null;
}
public User updateUserPassword(Long id, String newPassword) {
    User existingUser = userRepository.findById(id).orElse(null);
    if (existingUser != null) {
        existingUser.setPassword(newPassword);
        return userRepository.save(existingUser);
    }
    return null;
}
public boolean deleteUser(Long id) {
    if (userRepository.existsById(id)) {
        userRepository.deleteById(id);
        return true;
    }
    return false;
}
private UserResponse toUserResponse(User user) {
    return new UserResponse(
        user.getId(),
        user.getFirstname(),
        user.getLastname(),
        user.getEmail(),
        user.getRole()
    );
}
public UserResponse createSecretary(CreateSecretaryRequest request) {

    User secretary = new User();

    secretary.setFirstname(request.firstName());
    secretary.setLastname(request.lastName());
    secretary.setEmail(request.email());
    secretary.setPassword(passwordEncoder.encode(request.password()));
    secretary.setRole(Role.SECRETAIRE);
    secretary.setEnabled(true);

    User savedSecretary = userRepository.save(secretary);

    return toUserResponse(savedSecretary);
}






}
