package com.mariane.api_finance.service;

import com.mariane.api_finance.dto.TransactionRequestDTO;
import com.mariane.api_finance.dto.TransactionResponseDTO;
import com.mariane.api_finance.entity.Account;
import com.mariane.api_finance.entity.Transaction;
import com.mariane.api_finance.entity.enums.TransactionType;
import com.mariane.api_finance.repository.AccountRepository;
import com.mariane.api_finance.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionService(TransactionRepository transactionRepository,
                              AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    public TransactionResponseDTO createTransaction(TransactionRequestDTO dto) {

        Account account = accountRepository.findById(dto.accountId())
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        Transaction transaction = new Transaction();
        transaction.setDescription(dto.description());
        transaction.setAmount(dto.amount());
        transaction.setType(dto.type());
        transaction.setDate(LocalDate.from(LocalDateTime.now()));
        transaction.setAccount(account);

        // versao protegida contra saldo negativo
        if (dto.type() == TransactionType.INCOME) {

            account.setBalance(account.getBalance().add(dto.amount()));

        } else {

            BigDecimal newBalance = account.getBalance().subtract(dto.amount());

            if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
                throw new RuntimeException("Saldo insuficiente.");
            }

            account.setBalance(newBalance);
        }

        Transaction saved = transactionRepository.save(transaction);

        return new TransactionResponseDTO(
                saved.getId(),
                saved.getDescription(),
                saved.getAmount(),
                saved.getType(),
                saved.getDate().atStartOfDay()
        );
    }
}
