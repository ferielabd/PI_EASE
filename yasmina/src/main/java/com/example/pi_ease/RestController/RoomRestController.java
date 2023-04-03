package com.example.pi_ease.RestControllers;
import com.example.pi_ease.DAO.Entities.Message;
import com.example.pi_ease.DAO.Entities.Room;
import com.example.pi_ease.Services.Interface.IRoomServices;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class RoomRestController {
    private IRoomServices iRoomServices ;
    @GetMapping("/afficherRoom")
    public List<Room> afficher() {
        return iRoomServices.selectAll();

    }
    @PostMapping("/ajouterAllRoom")
    public List<Room> addAll(@RequestBody List<Room> list){
        return iRoomServices.addAll(list) ;}
    @PostMapping("/ajouterRoom")
    public Room ajouter(@RequestBody Room room){
        return  iRoomServices.add(room);
    }
    @GetMapping("/afficherRoomAvecId/{idRoom}")
    public Room afficherAvecId(@PathVariable int idRoom  )
    {
        return iRoomServices.selectById(idRoom);
    }
    @PutMapping("/modifierRoom")
    public Room edit(@RequestBody Room room){

        return iRoomServices.edit(room);
    }
    @DeleteMapping ("/deleteRoomById")
    public void deleteById(@RequestParam int idRoom ){

        iRoomServices.deleteByID(idRoom); ;
    }
    @DeleteMapping ("/deleteRoom")
    public void deleteByObject(@RequestParam Room room ){

        iRoomServices.delete(room);

    }
    @DeleteMapping("/deleteAllRoom")
    public void deleteAll(){
        iRoomServices.deleteAll();}
}







