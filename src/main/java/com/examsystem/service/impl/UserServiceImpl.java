package com.examsystem.service.impl;

import com.examsystem.data.entity.Role;
import com.examsystem.data.entity.User;
import com.examsystem.data.enums.RoleType;
import com.examsystem.data.repository.RoleRepository;
import com.examsystem.data.repository.UserRepository;
import com.examsystem.dto.request.RegisterRequest;
import com.examsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        Role role = roleRepository.findByName(String.valueOf(RoleType.ROLE_STUDENT))
                .orElseThrow(() -> new RuntimeException("Role not found"));

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .isEnabled(true)
                .build();

        return userRepository.save(user);
    }
}
