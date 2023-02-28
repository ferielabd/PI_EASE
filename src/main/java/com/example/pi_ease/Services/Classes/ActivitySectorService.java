package com.example.pi_ease.Services.Classes;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.pi_ease.DAO.Entities.ActivitySector;
import tn.esprit.pi_ease.DAO.Repositories.ActivitySectorRepository;
import tn.esprit.pi_ease.Services.Interfaces.IActivitySectorService;

import java.util.List;

@AllArgsConstructor
@Service
public class ActivitySectorService implements IActivitySectorService {

    ActivitySectorRepository activitySectorRepository;
    @Override
    public ActivitySector add(ActivitySector a) {
        return activitySectorRepository.save(a);
    }

    @Override
    public ActivitySector edit(ActivitySector a) {
        return activitySectorRepository.save(a);
    }

    @Override
    public List<ActivitySector> SelectAll() {
        return activitySectorRepository.findAll();
    }

    @Override
    public ActivitySector selectById(int IdAc) {
        return activitySectorRepository.findById(IdAc).get();
    }

    @Override
    public void deleteById(int IdAc) {
        activitySectorRepository.deleteById(IdAc);

    }
}
