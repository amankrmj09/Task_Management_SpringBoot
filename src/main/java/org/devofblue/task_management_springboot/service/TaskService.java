package org.devofblue.task_management_springboot.service;

import org.devofblue.task_management_springboot.dto.request.CreateTaskRequest;
import org.devofblue.task_management_springboot.dto.request.UpdateTaskStatusRequest;
import org.devofblue.task_management_springboot.dto.response.ApiResponse;
import org.devofblue.task_management_springboot.dto.response.TaskResponse;
import org.devofblue.task_management_springboot.enums.Priority;
import org.devofblue.task_management_springboot.enums.Role;
import org.devofblue.task_management_springboot.enums.TaskStatus;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface TaskService {
    ApiResponse<TaskResponse> createTask(UUID projectId, CreateTaskRequest request, UUID creatorId);

    ApiResponse<?> getTasks(UUID projectId, TaskStatus status, String assigneeEmail, Priority priority, boolean overdue, Pageable pageable);

    ApiResponse<TaskResponse> getTaskById(UUID projectId, UUID taskId, UUID userId, Role role);

    ApiResponse<TaskResponse> updateTask(UUID projectId, UUID taskId, CreateTaskRequest request, UUID userId, Role role);

    ApiResponse<TaskResponse> updateTaskStatus(UUID projectId, UUID taskId, UpdateTaskStatusRequest request, UUID userId, Role role);

    ApiResponse<?> addComment(UUID projectId, UUID taskId, String text, UUID authorId);

    ApiResponse<?> deleteComment(UUID projectId, UUID taskId, UUID commentId, UUID userId, Role role);

    ApiResponse<?> deleteTask(UUID projectId, UUID taskId);
}
