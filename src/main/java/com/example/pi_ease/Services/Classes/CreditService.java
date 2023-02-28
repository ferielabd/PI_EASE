package com.example.pi_ease.Services.Classes;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.pi_ease.DAO.Entities.Credit;
import tn.esprit.pi_ease.DAO.Repositories.CrediRepository;
import tn.esprit.pi_ease.Services.Interfaces.ICreditService;

import java.util.List;

@AllArgsConstructor
@Service
public class CreditService implements ICreditService {
    CrediRepository crediRepository;
    @Override
    public Credit add(Credit credit) {
        return crediRepository.save(credit);
    }

    @Override
    public Credit edit(Credit credit) {
        return crediRepository.save(credit);
    }

    @Override
    public List<Credit> SelectAll() {
        return crediRepository.findAll();
    }

    @Override
    public Credit selectById(int Idcredit) {
        return crediRepository.findById(Idcredit).get();
    }

    @Override
    public void deleteById(int Idcredit) {
        crediRepository.deleteById(Idcredit);

    }
}
