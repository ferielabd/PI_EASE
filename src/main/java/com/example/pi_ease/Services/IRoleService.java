package com.example.pi_ease.Services;

import com.example.pi_ease.DAO.Entities.Role;

import java.util.List;

public interface IRoleService {
    Role add(Role r);
    Role edit(Role R);
    List<Role> selectAll();
    Role selectById(Integer idRole);
    void deleteById(Integer idRole);
    void delete (Role R);
    List <Role> addAll (List<Role> list);
    void deleteAll(List<Role> list);
}
