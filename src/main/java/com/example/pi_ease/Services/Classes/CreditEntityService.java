package com.example.pi_ease.Services.Classes;

import com.example.pi_ease.DAO.Entities.Credit;
import com.example.pi_ease.DAO.Repositories.CrediRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CreditEntityService extends BaseEntityService<Credit, CrediRepository> {

    public CreditEntityService(CrediRepository dao){
        super(dao);
    }

}