package com.example.pi_ease.RestControllers;
import java.util.List;

import com.example.pi_ease.Services.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.pi_ease.DAO.Entities.Role;
import com.example.pi_ease.DAO.Repositories.RoleRepository;



@RestController
@RequestMapping({"/Roles"})
//@CrossOrigin(origins="*")
public class RoleController {
	@Autowired

    private IRoleService iRoleService;


    @GetMapping("/AfficherRole")
    public List<Role> afficherRole(){

        return iRoleService.selectAll();
    }

    @PostMapping("/ajouterRole")
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
    }

    @DeleteMapping("/deleteRoleById/{id}")
    public void deletetById (@PathVariable int id){

        iRoleService.deleteById(id);
    }


}
