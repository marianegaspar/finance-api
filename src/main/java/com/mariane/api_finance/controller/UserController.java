package com.mariane.api_finance.controller;

import com.mariane.api_finance.entity.User;
import com.mariane.api_finance.service.UserService;
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
    public User createUser(@RequestBody User user) {
        System.out.println("Password recebido: " + user.getPassword());
        return userService.createUser(user);
    }

    // Listar todos
    @GetMapping
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    // Buscar por id
    @GetMapping("/{id}")
    public User findUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }
}
