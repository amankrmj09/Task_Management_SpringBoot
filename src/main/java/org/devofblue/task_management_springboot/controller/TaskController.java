package org.devofblue.task_management_springboot.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.devofblue.task_management_springboot.dto.request.CreateTaskRequest;
import org.devofblue.task_management_springboot.dto.request.UpdateTaskStatusRequest;
import org.devofblue.task_management_springboot.dto.response.ApiResponse;
import org.devofblue.task_management_springboot.dto.response.TaskResponse;
import org.devofblue.task_management_springboot.enums.Priority;
import org.devofblue.task_management_springboot.enums.TaskStatus;
import org.devofblue.task_management_springboot.security.UserPrincipal;
import org.devofblue.task_management_springboot.service.TaskService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/projects/{projectId}/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<ApiResponse<TaskResponse>> createTask(
            @PathVariable UUID projectId,
            @Valid @RequestBody CreateTaskRequest request,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(taskService.createTask(projectId, request, userPrincipal.getId()));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getTasks(
            @PathVariable UUID projectId,
            @RequestParam(required = false) TaskStatus status,
            @RequestParam(required = false) String assigneeEmail,
            @RequestParam(required = false) Priority priority,
            @RequestParam(defaultValue = "false") boolean overdue,
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(taskService.getTasks(projectId, status, assigneeEmail, priority, overdue, pageable));
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<ApiResponse<TaskResponse>> getTaskById(
            @PathVariable UUID projectId,
            @PathVariable UUID taskId,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity.ok(taskService.getTaskById(
                projectId, taskId, userPrincipal.getId(), userPrincipal.getUser().getRole()));
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<ApiResponse<TaskResponse>> updateTask(
            @PathVariable UUID projectId,
            @PathVariable UUID taskId,
            @Valid @RequestBody CreateTaskRequest request,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity.ok(taskService.updateTask(
                projectId, taskId, request, userPrincipal.getId(), userPrincipal.getUser().getRole()));
    }

    @PatchMapping("/{taskId}/status")
    public ResponseEntity<ApiResponse<TaskResponse>> updateTaskStatus(
            @PathVariable UUID projectId,
            @PathVariable UUID taskId,
            @Valid @RequestBody UpdateTaskStatusRequest request,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity.ok(taskService.updateTaskStatus(
                projectId, taskId, request, userPrincipal.getId(), userPrincipal.getUser().getRole()));
    }

    @PostMapping("/{taskId}/comments")
    public ResponseEntity<ApiResponse<?>> addComment(
            @PathVariable UUID projectId,
            @PathVariable UUID taskId,
            @RequestBody Map<String, String> request,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(taskService.addComment(projectId, taskId, request.get("text"), userPrincipal.getId()));
    }

    @DeleteMapping("/{taskId}/comments/{commentId}")
    public ResponseEntity<ApiResponse<?>> deleteComment(
            @PathVariable UUID projectId,
            @PathVariable UUID taskId,
            @PathVariable UUID commentId,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity.ok(taskService.deleteComment(
                projectId, taskId, commentId, userPrincipal.getId(), userPrincipal.getUser().getRole()));
    }

    @DeleteMapping("/{taskId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<?>> deleteTask(
            @PathVariable UUID projectId,
            @PathVariable UUID taskId) {
        return ResponseEntity.ok(taskService.deleteTask(projectId, taskId));
    }
}
