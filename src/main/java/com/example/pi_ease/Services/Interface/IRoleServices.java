package com.example.pi_ease.Services.Interface;

import com.example.pi_ease.DAO.Entities.Role;

import java.util.List;

public interface IRoleServices {
    Role add(Role r);
    Role edit(Role R);
    List<Role> selectAll();
    Role selectById(Integer idRole);
    void deleteById(Integer idRole);
    void delete (Role R);
    List <Role> addAll (List<Role> list);
    void deleteAll(List<Role> list);
}
