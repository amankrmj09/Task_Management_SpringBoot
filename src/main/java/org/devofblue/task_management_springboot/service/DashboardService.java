package org.devofblue.task_management_springboot.service;

import org.devofblue.task_management_springboot.dto.response.ApiResponse;
import org.devofblue.task_management_springboot.dto.response.DashboardResponse;

import java.util.UUID;

public interface DashboardService {
    ApiResponse<DashboardResponse.MyDashboard> getMyDashboard(UUID userId);

    ApiResponse<DashboardResponse.AdminDashboard> getAdminDashboard();

    ApiResponse<DashboardResponse.ProjectStats> getProjectStats(UUID projectId, UUID userId, org.devofblue.task_management_springboot.enums.Role role);
}
