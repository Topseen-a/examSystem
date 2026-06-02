package com.examsystem.service;

import com.examsystem.dto.request.LoginRequest;
import com.examsystem.dto.request.RegisterRequest;
import com.examsystem.dto.response.AuthResponse;
import com.examsystem.dto.response.UserResponse;

public interface UserService {

    UserResponse registerUser(RegisterRequest user);

    AuthResponse login(LoginRequest user);
}
