package com.mariane.api_finance.dto;

public record LoginResponseDTO(
        Long id,
        String name,
        String email,
        String token) {}
