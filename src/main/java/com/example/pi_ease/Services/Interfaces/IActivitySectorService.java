package com.example.pi_ease.Services.Interfaces;

import com.example.pi_ease.DAO.Entities.ActivitySector;

import java.util.List;

public interface IActivitySectorService {
    ActivitySector add(ActivitySector a);
    ActivitySector edit(ActivitySector a);
    List<ActivitySector> SelectAll();
    ActivitySector selectById(int IdAc);
    void deleteById(int IdAc);

}
