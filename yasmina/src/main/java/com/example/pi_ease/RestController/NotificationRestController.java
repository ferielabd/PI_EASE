package com.example.pi_ease.RestControllers;

import com.example.pi_ease.DAO.Entities.Notification;
import com.example.pi_ease.Services.Interface.INotificationServices;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
public class NotificationRestController {

    private INotificationServices iNotifactionService;

    @GetMapping("/AfficherNotification")
    public List<Notification> afficherNotification(){
        return iNotifactionService.selectAll();
    }
    @PostMapping("/ajouterNotification")
    public Notification ajouterNotification(@RequestBody Notification notification){
        return iNotifactionService.add(notification);
    }

    @GetMapping("/AfficherNotificationavecId/{idNotification}")
    public Notification selectById(@PathVariable Integer idNotification) {
        return iNotifactionService.selectById(idNotification);}

    @PutMapping("/ModifierNotification")
    public Notification edit(@RequestBody Notification notification) {

        return iNotifactionService.edit(notification);
    }

    @DeleteMapping("/deleteNotificationById/{id}")
    public void deletetById (@PathVariable Integer id){
        iNotifactionService.deleteById(id);
    }



}
