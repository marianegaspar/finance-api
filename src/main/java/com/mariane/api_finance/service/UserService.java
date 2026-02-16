package com.mariane.api_finance.service;


import com.mariane.api_finance.entity.Account;
import com.mariane.api_finance.entity.User;
import com.mariane.api_finance.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ✅ Criar usuário
    public User createUser(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email já cadastrado.");
        }

        Account account = new Account();

        account.setUser(user);     // lado dono
        user.setAccount(account);  // lado inverso

        return userRepository.save(user);
    }

    // ✅ Listar todos
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    // ✅ Buscar por ID
    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
}