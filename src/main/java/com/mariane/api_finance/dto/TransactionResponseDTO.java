package com.mariane.api_finance.dto;

import com.mariane.api_finance.entity.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionResponseDTO(
        Long id,
        String description,
        BigDecimal amount,
        TransactionType type,
        LocalDateTime date
) {}
