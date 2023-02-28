package com.example.pi_ease.Services.Interfaces;

import tn.esprit.pi_ease.DAO.Entities.ActivitySector;
import tn.esprit.pi_ease.DAO.Entities.Credit;

import java.util.List;

public interface ICreditService {

    Credit add(Credit credit);
    Credit edit(Credit credit);
    List<Credit> SelectAll();
    Credit selectById(int Idcredit);
    void deleteById(int Idcredit);
}
