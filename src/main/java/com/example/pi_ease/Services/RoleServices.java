package com.example.pi_ease.Services;

import com.example.pi_ease.DAO.Entities.Role;
import com.example.pi_ease.DAO.Repositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleServices implements IRoleService{
    private RoleRepository roleRepository;
    @Override
    public Role add(Role r) {
        return roleRepository.save(r);
    }
    @Override
    public Role edit(Role R) {

        return roleRepository.save(R);
    }

    @Override
    public List<Role> selectAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role selectById(Integer id) {

        return roleRepository.findById(id).get(id);
    }

    @Override
    public void deleteById(Integer idRole) {
        roleRepository.deleteById(idRole);

    }

    @Override
    public void delete(Role R) {
        roleRepository.delete(R);
    }

    @Override
    public List<Role> addAll(List<Role> list) {
        return roleRepository.saveAll(list);
    }

    @Override
    public void deleteAll(List<Role> list) {
        roleRepository.deleteAll(list);
    }
}
