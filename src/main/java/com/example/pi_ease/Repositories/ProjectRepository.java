package com.example.pi_ease.Repositories;

import com.example.pi_ease.DAO.Entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project,Integer> {
}
