package com.example.pi_ease.Services.Classes;


import com.example.pi_ease.DAO.Repositories.ActivitySectorRepository;
import com.example.pi_ease.DAO.Repositories.CrediRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.pi_ease.DAO.Entities.ActivitySector;


import java.util.List;

@AllArgsConstructor
@Service
public class ActivitySectorService extends BaseEntityService<ActivitySector, ActivitySectorRepository> {


    @Autowired
    public ActivitySectorService(ActivitySectorRepository repository) {
        super();
        this.setDao(repository);
    }
}
