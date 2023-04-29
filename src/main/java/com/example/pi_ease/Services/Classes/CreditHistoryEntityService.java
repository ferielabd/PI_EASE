package com.example.pi_ease.Services.Classes;

import com.example.pi_ease.DAO.Entities.Credit;
import com.example.pi_ease.DAO.Entities.CreditHistory;
import com.example.pi_ease.DAO.Repositories.CrediRepository;
import com.example.pi_ease.DAO.Repositories.CreditHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditHistoryEntityService extends BaseEntityService<CreditHistory, CreditHistoryRepository> {


    @Autowired
    public CreditHistoryEntityService(CreditHistoryRepository repository) {
        super();
        this.setDao(repository);
    }
}