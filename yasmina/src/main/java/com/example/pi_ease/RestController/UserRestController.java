package com.example.pi_ease.RestControllers;

import com.example.pi_ease.DAO.Entities.User;
import com.example.pi_ease.Services.Interface.IUserServices;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
public class UserRestController {
    private IUserServices iUserService;
    @GetMapping("/AfficherUser")
    public List<User> afficherUser(){
        return iUserService.selectAll();
    }

    @PostMapping("/ajouterUser")
    public User ajouterUser(@RequestBody User user){
        return iUserService.add(user);
    }

    @GetMapping("/afficherUserAvecId/{id}")
    public User afficherUserAvecId(@PathVariable long id){

        return iUserService.selectById(id);
    }

    @PutMapping("/ModifierUser")
    public User edit(@RequestBody User user) {
        return iUserService.edit(user);
    }

    @DeleteMapping("/deleteUserById/{id}")
    public void deletetById (@PathVariable long id){
        iUserService.deleteById(id);
    }


    @GetMapping("/active/{id}")
    public void active(@PathVariable long id){

        iUserService.active(id);
    }
}
