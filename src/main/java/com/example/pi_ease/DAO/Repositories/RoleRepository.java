package com.example.pi_ease.DAO.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pi_ease.DAO.Entities.TypeRole;
import com.example.pi_ease.DAO.Entities.Role;
import org.springframework.stereotype.Service;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(TypeRole name);

	List <Role> findById(Integer id);

	void deleteById(Integer idRole);
}
