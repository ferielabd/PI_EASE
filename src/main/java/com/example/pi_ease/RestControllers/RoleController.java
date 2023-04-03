package com.example.pi_ease.RestControllers;

import com.example.pi_ease.DAO.Entities.Role;
import com.example.pi_ease.Services.Interface.IRoleServices;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@AllArgsConstructor
public class RoleController {

    private IRoleServices iRoleService;
    @GetMapping("/AfficherRole")
    public List<Role> afficherRole(){

        return iRoleService.selectAll();
    }

    @PostMapping("/ajouterRole")
    public Role ajouterRole(@RequestBody Role role){
        System.out.println("Role"+role.getTypeRole());
        return iRoleService.add(role);
    }

    @GetMapping("/afficherRoleAvecId/{idRole}")
    public Role afficherRoleAvecId(@PathVariable int idRole){
        return iRoleService.selectById(idRole);
    }

    @PutMapping("/ModifierRole")
    public Role edit(@RequestBody Role role) {

        return iRoleService.edit(role);
    }

    @DeleteMapping("/deleteRoleById/{id}")
    public void deletetById (@PathVariable int id){

        iRoleService.deleteById(id);
    }




}
