package com.example.pi_ease.Services.Classes;

import com.example.pi_ease.DAO.Entities.Tranche;
import com.example.pi_ease.DAO.Repositories.TrancheRepository;

public class TrancheEntityService extends BaseEntityService<Tranche, TrancheRepository> {
    public TrancheEntityService(TrancheRepository dao){
        super(dao);
    }
}
