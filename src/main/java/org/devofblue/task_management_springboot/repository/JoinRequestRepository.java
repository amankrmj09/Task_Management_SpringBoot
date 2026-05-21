package org.devofblue.task_management_springboot.repository;

import org.devofblue.task_management_springboot.entity.JoinRequest;
import org.devofblue.task_management_springboot.entity.Project;
import org.devofblue.task_management_springboot.entity.User;
import org.devofblue.task_management_springboot.enums.JoinRequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JoinRequestRepository extends JpaRepository<JoinRequest, UUID> {
    List<JoinRequest> findByProjectId(UUID projectId);

    List<JoinRequest> findByProjectIdAndStatus(UUID projectId, JoinRequestStatus status);

    Optional<JoinRequest> findByProjectAndUser(Project project, User user);

    boolean existsByProjectAndUserAndStatus(Project project, User user, JoinRequestStatus status);

    List<JoinRequest> findByStatus(JoinRequestStatus status);
}
