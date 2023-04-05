package com.example.pi_ease.RestControllers;
<<<<<<< HEAD

import com.example.pi_ease.DAO.Entities.Role;
import com.example.pi_ease.Services.Interface.IRoleServices;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
=======
import java.util.List;

import com.example.pi_ease.Services.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.pi_ease.DAO.Entities.Role;

>>>>>>> 1d607ee2204a46c9189a5d07d71b3c328a205d9b



@RestController
<<<<<<< HEAD
@AllArgsConstructor
public class RoleController {

    private IRoleServices iRoleService;
=======
@RequestMapping({"/Roles"})
public class RoleController {
	@Autowired
    private IRoleService iRoleService;

    public RoleController(IRoleService iRoleService) {
        this.iRoleService = iRoleService;
    }

>>>>>>> 1d607ee2204a46c9189a5d07d71b3c328a205d9b
    @GetMapping("/AfficherRole")
    public List<Role> afficherRole(){

        return iRoleService.selectAll();
    }

    @PostMapping("/ajouterRole")
<<<<<<< HEAD
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
=======
    public Role ajouterRole(@RequestBody Role name){
        System.out.println("Role"+name.getName());
        return iRoleService.add(name);
    }

    @GetMapping("/afficherRoleAvecId/{id}")
    public Role afficherRoleAvecId(@PathVariable int id){
        return iRoleService.selectById(id);
    }

    @PutMapping("/ModifierRole")
    public Role edit(@RequestBody Role name) {

        return iRoleService.edit(name);
>>>>>>> 1d607ee2204a46c9189a5d07d71b3c328a205d9b
    }

    @DeleteMapping("/deleteRoleById/{id}")
    public void deletetById (@PathVariable int id){

        iRoleService.deleteById(id);
    }


<<<<<<< HEAD


=======
>>>>>>> 1d607ee2204a46c9189a5d07d71b3c328a205d9b
}
