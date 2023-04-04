package com.example.pi_ease.Services.Classes;

import com.example.pi_ease.DAO.Entities.Credit;
import com.example.pi_ease.DAO.Repositories.CrediRepository;
import org.springframework.stereotype.Service;

@Service
public class CreditEntityService extends BaseEntityService<Credit, CrediRepository> {

    public CreditEntityService(CrediRepository repository) {
        super();
        this.setDao(repository);
    }
}