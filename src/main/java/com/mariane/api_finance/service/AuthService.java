package com.mariane.api_finance.service;

import com.mariane.api_finance.dto.LoginRequestDTO;
import com.mariane.api_finance.dto.LoginResponseDTO;
import com.mariane.api_finance.entity.User;
import com.mariane.api_finance.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService  jwtService;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public LoginResponseDTO login(LoginRequestDTO dto) {

        User user = userRepository.findByEmail(dto.email())
                .orElseThrow(() -> new RuntimeException("Email ou senha inválidos"));

        if (!passwordEncoder.matches(dto.password(), user.getPassword())) {
            throw new RuntimeException("Email ou senha inválidos");
        }

        //pra gerar token
        String token = jwtService.generateToken(String.valueOf(user));

        return new LoginResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                token
        );
    }
}