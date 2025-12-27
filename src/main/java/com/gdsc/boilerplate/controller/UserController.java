package com.gdsc.boilerplate.controller;

import com.gdsc.boilerplate.helper.ResponseHelper;
import com.gdsc.boilerplate.security.JwtService;
import com.gdsc.boilerplate.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Tag(name = "User", description = "User management APIs")
public class UserController {
    
    private final UserService userService;
    private final JwtService jwtService;
    
    
    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID", description = "Get user information by user ID")
    public ResponseEntity<?> getById(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable UUID id
    ) {
        try {
            validateToken(authHeader);

            var user = userService.findById(id);
            return ResponseHelper.success("User fetched successfully", user);

        } catch (Exception e) {
            return ResponseHelper.error(e.getMessage());
        }
    }

    @GetMapping
    @Operation(summary = "Get all users", description = "Get list of all users")
    public ResponseEntity<?> getAll(
            @RequestHeader("Authorization") String authHeader
    ) {
        try {
            validateToken(authHeader);

            var users = userService.findAll();
            return ResponseHelper.success("Users fetched successfully", users);

        } catch (Exception e) {
            return ResponseHelper.error(e.getMessage());
        }
    }

    private void validateToken(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Missing or invalid Authorization header");
        }

        String token = authHeader.substring(7);
        jwtService.extractUserId(token);
    }
}
