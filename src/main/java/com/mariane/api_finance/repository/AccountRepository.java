package com.mariane.api_finance.repository;

import com.mariane.api_finance.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
