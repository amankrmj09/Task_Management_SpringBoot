package org.devofblue.task_management_springboot.controller;

import lombok.RequiredArgsConstructor;
import org.devofblue.task_management_springboot.dto.response.ApiResponse;
import org.devofblue.task_management_springboot.dto.response.DashboardResponse;
import org.devofblue.task_management_springboot.security.UserPrincipal;
import org.devofblue.task_management_springboot.service.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/my")
    public ResponseEntity<ApiResponse<DashboardResponse.MyDashboard>> getMyDashboard(
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity.ok(dashboardService.getMyDashboard(userPrincipal.getId()));
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<DashboardResponse.AdminDashboard>> getAdminDashboard() {
        return ResponseEntity.ok(dashboardService.getAdminDashboard());
    }

    @GetMapping("/projects/{projectId}/stats")
    public ResponseEntity<ApiResponse<DashboardResponse.ProjectStats>> getProjectStats(
            @PathVariable UUID projectId,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity.ok(dashboardService.getProjectStats(
                projectId, userPrincipal.getId(), userPrincipal.getUser().getRole()));
    }
}
