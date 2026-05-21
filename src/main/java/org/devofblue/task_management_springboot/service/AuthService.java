package org.devofblue.task_management_springboot.service;

import org.devofblue.task_management_springboot.dto.request.LoginRequest;
import org.devofblue.task_management_springboot.dto.request.RegisterRequest;
import org.devofblue.task_management_springboot.dto.response.ApiResponse;
import org.devofblue.task_management_springboot.dto.response.AuthResponse;

public interface AuthService {
    ApiResponse<AuthResponse> register(RegisterRequest request);

    ApiResponse<AuthResponse> login(LoginRequest request);

    ApiResponse<AuthResponse> refreshToken(String refreshToken);

    ApiResponse<Void> logout(String refreshToken);
}
