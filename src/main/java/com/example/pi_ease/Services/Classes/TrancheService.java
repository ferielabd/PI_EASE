package com.example.pi_ease.Services.Classes;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.pi_ease.DAO.Entities.Tranche;
import tn.esprit.pi_ease.DAO.Repositories.TrancheRepository;
import tn.esprit.pi_ease.Services.Interfaces.ITrancheService;

import java.util.List;

@AllArgsConstructor
@Service
public class TrancheService implements ITrancheService {
    TrancheRepository trancheRepository;
    @Override
    public Tranche add(Tranche tranche) {
        return trancheRepository.save(tranche);
    }

    @Override
    public Tranche edit(Tranche tranche) {
        return trancheRepository.save(tranche);
    }

    @Override
    public List<Tranche> SelectAll() {
        return trancheRepository.findAll();
    }

    @Override
    public Tranche selectById(int IdT) {
        return trancheRepository.findById(IdT).get();
    }

    @Override
    public void deleteById(int IdT) {
        trancheRepository.deleteById(IdT);

    }
}
