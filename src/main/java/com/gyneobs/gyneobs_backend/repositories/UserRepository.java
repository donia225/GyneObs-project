package com.gyneobs.gyneobs_backend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gyneobs.gyneobs_backend.entities.User;
import com.gyneobs.gyneobs_backend.enums.Role;

public interface UserRepository extends JpaRepository<User, Long> {
        Optional<User> findByEmail(String email);
        List<User> findByRole(Role role);


}
