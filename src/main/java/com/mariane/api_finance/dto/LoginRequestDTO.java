package com.mariane.api_finance.dto;

public record LoginRequestDTO(
        String email,
        String password
) {}