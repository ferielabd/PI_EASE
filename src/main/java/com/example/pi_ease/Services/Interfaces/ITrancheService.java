package com.example.pi_ease.Services.Interfaces;

import tn.esprit.pi_ease.DAO.Entities.ActivitySector;
import tn.esprit.pi_ease.DAO.Entities.Credit;
import tn.esprit.pi_ease.DAO.Entities.Tranche;

import java.util.List;

public interface ITrancheService {
    Tranche add(Tranche tranche);
    Tranche edit(Tranche tranche);
    List<Tranche> SelectAll();
    Tranche selectById(int IdT);
    void deleteById(int IdT);
}
