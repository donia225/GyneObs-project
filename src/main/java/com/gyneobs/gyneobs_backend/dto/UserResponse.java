package com.gyneobs.gyneobs_backend.dto;
import com.gyneobs.gyneobs_backend.enums.Role;

public record UserResponse (
    long id,
    String firstName,
    String lastName,
    String email,
    Role role
) {}



