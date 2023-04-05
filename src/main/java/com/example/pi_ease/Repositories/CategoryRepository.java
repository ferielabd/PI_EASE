package com.example.pi_ease.Repositories;

import com.example.pi_ease.DAO.Entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
