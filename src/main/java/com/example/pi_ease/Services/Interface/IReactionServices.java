package com.example.pi_ease.Services.Interface;

import com.example.pi_ease.DAO.Entities.Reaction;


import java.util.List;

public interface IReactionServices {
    Reaction add(Reaction Re);
    Reaction edit(Reaction Re);
    List<Reaction> selectAll();
    Reaction selectById(int idR);
    void deleteByID (int idR);
    void delete (Reaction Re);
    List<Reaction>addAll(List<Reaction> list);
    void deleteAll ();
}
