package com.gdsc.boilerplate.service;


import org.springframework.security.crypto.password.PasswordEncoder;

import com.gdsc.boilerplate.dto.request.LoginRequest;
import com.gdsc.boilerplate.dto.request.RegisterRequest;
import com.gdsc.boilerplate.model.User;
import com.gdsc.boilerplate.model.Role;
import com.gdsc.boilerplate.repository.UserRepository;
import com.gdsc.boilerplate.kafka.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final KafkaProducer kafkaProducer;

    public User login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return user;
    }
    public User register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setFullname(request.getFullname());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);
        User usersaved = userRepository.save(user);

        kafkaProducer.sendMessage("user-created", usersaved.getId().toString(), usersaved);

        return usersaved;
    }
}
