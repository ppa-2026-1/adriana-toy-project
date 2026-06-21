package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.repository.TokenRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.entity.Token;

@Service
public class AuthService {

    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    AuthService(TokenRepository tokenRepository, UserRepository userRepository) {
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public String validateToken(String token) {
        return tokenRepository.findByValue(token)
                .filter(t -> t.getExpiresAt().isAfter(LocalDateTime.now()))
                .map(Token::getUserHandle)
                .orElse(null);
    }

    public String login(String username, String password) {
        var user = userRepository.findByEmail(username)
                .orElseThrow(() -> new IllegalArgumentException("Credenciais inválidas"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Credenciais inválidas");
        }

        Token token = new Token();
        token.setValue(UUID.randomUUID().toString());
        token.setUserHandle(user.getHandle());
        token.setExpiresAt(LocalDateTime.now().plusHours(24));

        tokenRepository.save(token);
        return token.getValue();
    }

    public void logout(String tokenValue) {
        tokenRepository.deleteByValue(tokenValue);
    }
}
