package com.gdsc.boilerplate.service;

import com.gdsc.boilerplate.dto.request.CreateUserRequest;
import com.gdsc.boilerplate.model.Role;
import com.gdsc.boilerplate.model.User;
import com.gdsc.boilerplate.repository.UserRepository;
import com.gdsc.boilerplate.validation.UserValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    public User findById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User create(CreateUserRequest request) {
        User user = new User();
        user.setFullname(request.getFullname());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setAddress(request.getAddress());
        user.setIdentityNumber(request.getIdentityNumber());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setRole(Role.USER);
        
        UserValidation.validateCreate(user);
        String lastCode = userRepository.findTopByOrderByCodeDesc()
                .map(User::getCode)
                .orElse("PTN000");
        String newCode = String.format("PTN%03d", Integer.parseInt(lastCode.substring(3)) + 1);
        user.setCode(newCode);
        return userRepository.save(user);
    }
    
    public User update(UUID id, CreateUserRequest request) {
        User existingUser = findById(id);
        if (existingUser == null) {
            throw new RuntimeException("User not found");
        }
        existingUser.setFullname(request.getFullname());
        existingUser.setEmail(request.getEmail());
        existingUser.setPassword(passwordEncoder.encode(request.getPassword()));
        existingUser.setAddress(request.getAddress());
        existingUser.setIdentityNumber(request.getIdentityNumber());
        existingUser.setPhoneNumber(request.getPhoneNumber());
        existingUser.setRole(Role.valueOf(request.getRole().toUpperCase()));
        
        UserValidation.validateUpdate(existingUser);
        return userRepository.save(existingUser);
    }

    public List<User> findByRole(Role role) {
        return userRepository.findAllByRole(role);
    }
    
    public void delete(UUID id) {
        User existingUser = findById(id);
        if (existingUser == null) {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
    }
}
