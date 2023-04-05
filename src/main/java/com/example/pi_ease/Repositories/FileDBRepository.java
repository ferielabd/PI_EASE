package com.example.pi_ease.Repositories;

import com.example.pi_ease.DAO.Entities.FileDb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileDBRepository  extends JpaRepository<FileDb,String>  {
}
