package com.examsystem.service.impl;

import com.examsystem.data.model.Role;
import com.examsystem.data.model.User;
import com.examsystem.data.enums.RoleType;
import com.examsystem.data.repository.RoleRepository;
import com.examsystem.data.repository.UserRepository;
import com.examsystem.dto.request.RegisterRequest;
import com.examsystem.dto.response.UserResponse;
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
    public UserResponse registerUser(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        Role role = roleRepository.findByName(RoleType.ROLE_STUDENT)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .isEnabled(true)
                .build();

        User savedUser = userRepository.save(user);

        return UserResponse.builder()
                .id(savedUser.getId())
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .email(savedUser.getEmail())
                .role(savedUser.getRole().getName().name())
                .build();
    }
}
