package com.mariane.api_finance.repository;

import com.mariane.api_finance.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email); //buscar usuário por email para login

    boolean existsByEmail(String email); //validar se o email já existe antes de cadastrar
}