package com.example.pi_ease.Repositories;

import com.example.pi_ease.DAO.Entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ProjectRepository extends JpaRepository<Project,Integer> {
    Project findAllByStartedAtBeforeAndFinishedAtAfter(LocalDateTime startedAt, LocalDateTime finishedAt);
    Project findByIdP(int idP);
}
