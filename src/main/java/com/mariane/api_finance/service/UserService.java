package com.mariane.api_finance.service;


import com.mariane.api_finance.dto.UserResponseDTO;
import com.mariane.api_finance.dto.UserRequestDTO;
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
    public UserResponseDTO createUser(UserRequestDTO dto) {

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email já cadastrado.");
        }

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        Account account = new Account();
        account.setUser(user);
        user.setAccount(account);

        User savedUser = userRepository.save(user);

        return new UserResponseDTO(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getAccount().getBalance()
        );
    }

    // ✅ Listar todos
    public List<UserResponseDTO> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> new UserResponseDTO(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getAccount().getBalance()
                ))
                .toList();
    }

    // ✅ Buscar por ID
    public UserResponseDTO findUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getAccount().getBalance()
        );
    }

}