package com.gdsc.boilerplate.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import com.gdsc.boilerplate.security.JwtService;
import com.gdsc.boilerplate.service.AuthService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

import com.gdsc.boilerplate.dto.request.LoginRequest;
import com.gdsc.boilerplate.dto.request.RegisterRequest;

import com.gdsc.boilerplate.model.User;
import com.gdsc.boilerplate.helper.ResponseHelper;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Auth APIs")
public class AuthController {
    
    private final AuthService authService;
    private final JwtService jwtService;

    @Operation(summary = "User login", description = "Authenticate user and return JWT token")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            var user = authService.login(request);
            String token = jwtService.generateToken(user);

            return ResponseHelper.success(
                    "Login successful",
                    Map.of(
                        "token", token,
                        "type", "Bearer"
                    )
            );

        } catch (Exception e) {
            return ResponseHelper.error(e.getMessage());
        }
    }
    
    @Operation(summary = "User registration", description = "Register a new user")
    @PostMapping("/register")
    @Tag(name = "Register", description = "User registration endpoint")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            User user = authService.register(request);
            return ResponseHelper.success("Registration successful", Map.of("user", user));
        } catch (Exception e) {
            return ResponseHelper.error(e.getMessage());
        }
    }
}
