package org.devofblue.task_management_springboot.service;

import org.devofblue.task_management_springboot.dto.response.ApiResponse;
import org.devofblue.task_management_springboot.enums.Role;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UserService {
    ApiResponse<?> getCurrentUser(UUID userId);

    ApiResponse<?> updateCurrentUser(UUID userId, String name, String password);

    ApiResponse<?> getAllUsers(Pageable pageable);

    ApiResponse<?> updateUserRole(UUID userId, Role role);

    ApiResponse<?> deleteUser(UUID userId);
}
