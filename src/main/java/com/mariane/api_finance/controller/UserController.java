package com.mariane.api_finance.controller;

import com.mariane.api_finance.dto.UserRequestDTO;
import com.mariane.api_finance.dto.UserResponseDTO;
import com.mariane.api_finance.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Criar usuário

    @PostMapping
    public UserResponseDTO createUser(@RequestBody @Valid UserRequestDTO dto) {
        return userService.createUser(dto);
    }

    // Listar todos
    @GetMapping
    public List<UserResponseDTO> findAllUsers() {

        return userService.findAllUsers();
    }

    // Buscar por id
    @GetMapping("/{id}")
    public UserResponseDTO findUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }
}
