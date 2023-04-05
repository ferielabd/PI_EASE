package com.example.pi_ease.Services.Classes;

import com.example.pi_ease.DAO.Entities.Checks;
import com.example.pi_ease.DAO.Repositories.checkRepo;
import com.example.pi_ease.Services.Interfaces.ICheckService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@Service
@AllArgsConstructor
public class CheckService implements ICheckService {
    private checkRepo checkrepo;
    @Override
    public Checks add(Checks c) {
        return checkrepo.save(c);
    }


    @Override
    public Checks edit(Checks c) {
        return checkrepo.save(c);
    }

    @Override
    public  List<Checks> selectAll() {
        return checkrepo.findAll();
    }

    @Override
    public Checks selectById(Integer idC) {
        return checkrepo.findById(idC).get();
    }

    @Override
    public void deleteById(Integer idC) {
        checkrepo.deleteById(idC);

    }

    @Override
    public void delete(Checks c) {
        checkrepo.delete(c);

    }

    @Override
    public List<Checks> addAll(List<Checks> list) {
        return checkrepo.saveAll(list);
    }

    @Override
    public void deleteAll(List<Checks> list) {
        checkrepo.deleteAll(list);

    }
    public List<Checks> getChecks(){
        return  checkrepo.findAll();
    }
}
