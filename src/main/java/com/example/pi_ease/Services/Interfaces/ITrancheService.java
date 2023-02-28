package com.example.pi_ease.Services.Interfaces;


import com.example.pi_ease.DAO.Entities.Tranche;

import java.util.List;

public interface ITrancheService {
    Tranche add(Tranche tranche);
    Tranche edit(Tranche tranche);
    List<Tranche> SelectAll();
    Tranche selectById(int IdT);
    void deleteById(int IdT);
}
