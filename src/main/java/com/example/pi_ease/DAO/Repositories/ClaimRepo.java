package com.example.pi_ease.DAO.Repositories;
import com.example.pi_ease.DAO.Entities.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Date;
import java.util.List;
@Repository
public interface ClaimRepo extends JpaRepository<Claim,Integer>{
    List<Claim> findByRefclaim(String type);
    List<Claim> findByDateClaim(Date date);

}
