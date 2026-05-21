package org.devofblue.task_management_springboot.service;

import org.devofblue.task_management_springboot.dto.response.ApiResponse;
import org.devofblue.task_management_springboot.dto.response.JoinRequestResponse;
import org.devofblue.task_management_springboot.enums.Role;

import java.util.List;
import java.util.UUID;

public interface JoinRequestService {
    ApiResponse<JoinRequestResponse> createJoinRequest(UUID projectId, UUID userId);

    ApiResponse<List<JoinRequestResponse>> getJoinRequests(UUID projectId, String status, UUID userId, Role role);

    ApiResponse<JoinRequestResponse> updateJoinRequestStatus(UUID projectId, UUID requestId, String status, UUID userId, Role role);
}
