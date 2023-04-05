package com.example.pi_ease.DAO.Repositories;

import com.example.pi_ease.DAO.Entities.TypeUser;
import com.example.pi_ease.DAO.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String username);


    boolean existsByUsername(String username);

    boolean existsByMail(String mail);

    Optional<User> findByMail(String mail);

}



