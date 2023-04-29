package com.example.pi_ease.DAO.Repositories;

import com.example.pi_ease.DAO.Entities.ActivitySector;
import com.example.pi_ease.DAO.Entities.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivitySectorRepository extends JpaRepository<ActivitySector,Long> {
}
