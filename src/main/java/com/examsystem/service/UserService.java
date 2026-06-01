package com.examsystem.service;

import com.examsystem.data.entity.User;
import com.examsystem.dto.request.RegisterRequest;

public interface UserService {

    User registerUser(RegisterRequest user);
}
