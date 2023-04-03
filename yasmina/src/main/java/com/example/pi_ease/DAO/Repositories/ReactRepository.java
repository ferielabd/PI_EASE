package com.example.pi_ease.DAO.Repositories;

import com.example.pi_ease.DAO.Entities.Post;
import com.example.pi_ease.DAO.Entities.React;
import com.example.pi_ease.DAO.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReactRepository extends CrudRepository<React,Integer>
{

    @Query("SELECT r FROM React r WHERE r.PostLike= :post")
    public List<React> findReactByPostId(@Param("post") Post Post);

    @Query("SELECT r FROM React r WHERE r.UserLike= :user")
    public List<React> findReactById(@Param("user") User User);
}
