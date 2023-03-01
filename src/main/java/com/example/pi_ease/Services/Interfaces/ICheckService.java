package com.example.pi_ease.Services.Interfaces;

import com.example.pi_ease.DAO.Entities.Checks;

import java.util.List;

public interface ICheckService {
    Checks add(Checks c);
    Checks edit(Checks c);
    List<Checks> selectAll();
    Checks selectById(Integer idC);

    void deleteById(Integer idC) ;

    void delete(Checks c);
    List<Checks> addAll(List<Checks> list);
    void deleteAll(List<Checks> list);
}
