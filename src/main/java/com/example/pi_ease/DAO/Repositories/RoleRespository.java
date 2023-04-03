package com.example.pi_ease.DAO.Repositories;

import com.example.pi_ease.DAO.Entities.Role;
import com.example.pi_ease.DAO.Entities.TypeRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RoleRespository extends JpaRepository<Role,Integer> {
    Optional<Role> findByTypeRole (TypeRole typeRole);

}
