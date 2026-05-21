package org.devofblue.task_management_springboot.repository;

import org.devofblue.task_management_springboot.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProjectRepository extends JpaRepository<Project, UUID> {

    java.util.List<Project> findTop5ByOrderByCreatedAtDesc();

    java.util.List<Project> findByNameContainingIgnoreCase(String name);
}
