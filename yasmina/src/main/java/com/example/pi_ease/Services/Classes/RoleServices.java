package com.example.pi_ease.Services.Classes;

import com.example.pi_ease.DAO.Entities.Role;
import com.example.pi_ease.DAO.Repositories.RoleRespository;
import com.example.pi_ease.Services.Interface.IRoleServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class RoleServices implements IRoleServices {

    private RoleRespository roleRespository;
    @Override
    public Role add(Role r) {
        return roleRespository.save(r);
    }

    @Override
    public Role edit(Role R) {

        return roleRespository.save(R);
    }

    @Override
    public List<Role> selectAll() {
        return roleRespository.findAll();
    }

    @Override
    public Role selectById(Integer idRole) {

        return roleRespository.findById(idRole).get();
    }

    @Override
    public void deleteById(Integer idRole) {
        roleRespository.deleteById(idRole);

    }

    @Override
    public void delete(Role R) {
        roleRespository.delete(R);
    }

    @Override
    public List<Role> addAll(List<Role> list) {
        return roleRespository.saveAll(list);
    }

    @Override
    public void deleteAll(List<Role> list) {
        roleRespository.deleteAll(list);
    }
}
