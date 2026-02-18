package com.mariane.api_finance.dto;

import com.mariane.api_finance.entity.enums.TransactionType;

import java.math.BigDecimal;

public record TransactionRequestDTO(
        String description,
        BigDecimal amount,
        TransactionType type,
        Long accountId
) {}
