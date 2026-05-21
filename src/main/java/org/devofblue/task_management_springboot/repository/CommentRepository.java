package org.devofblue.task_management_springboot.repository;

import org.devofblue.task_management_springboot.entity.Comment;
import org.devofblue.task_management_springboot.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {
    List<Comment> findAllByTaskOrderByCreatedAtAsc(Task task);
}
