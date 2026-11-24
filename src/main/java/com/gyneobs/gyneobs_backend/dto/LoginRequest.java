package com.gyneobs.gyneobs_backend.dto;

public record LoginRequest(
    String email,
    String password
) {}