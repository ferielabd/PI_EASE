package com.example.pi_ease.Services.Classes;

import com.example.pi_ease.DAO.Entities.Tranche;
import com.example.pi_ease.DAO.Repositories.TrancheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class TrancheEntityService extends BaseEntityService<Tranche, TrancheRepository> {
    public TrancheEntityService(TrancheRepository dao){
        super();
        this.setDao(dao);
    }
}
