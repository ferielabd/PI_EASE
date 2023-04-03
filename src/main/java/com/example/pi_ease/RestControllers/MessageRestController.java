package com.example.pi_ease.RestControllers;

import com.example.pi_ease.DAO.Entities.Message;
import com.example.pi_ease.Services.Interface.IMessageServices;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class MessageRestController {
    private IMessageServices iMessageServices;
    @GetMapping("/afficherMessage")
    public List<Message> afficher() {
        return iMessageServices.selectAll();

    }
    @PostMapping("/ajouterMessage")
    public Message ajouter(@RequestBody Message message){

        return  iMessageServices.add(message);
    }
    @PostMapping("/ajouterAllMessage")
    public List<Message> addAll(@RequestBody List<Message> list){
        return iMessageServices.addAll(list) ;}
    @GetMapping("/afficherMessageAvecId/{idMesg}")
    public Message afficherAvecId(@PathVariable int idMesg ){

        return iMessageServices.selectById(idMesg);
    }
    @PutMapping("/modifierMessage")
    public Message edit(@RequestBody Message message ){

        return iMessageServices.edit(message);
    }
    @DeleteMapping ("/deleteMessageById")
    public void deleteById(@RequestParam int idMesg ){

        iMessageServices.deleteByID(idMesg); ;
    }
    @DeleteMapping ("/deleteMessage")
    public void deleteByObject(@RequestParam Message message ){

        iMessageServices.delete(message);
    }
    @DeleteMapping("/deleteAllMessage")
    public void deleteAll(){
        iMessageServices.deleteAll();}
}
