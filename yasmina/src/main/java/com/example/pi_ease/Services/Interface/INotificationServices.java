package com.example.pi_ease.Services.Interface;

import com.example.pi_ease.DAO.Entities.Notification;

import java.util.List;

public interface INotificationServices {
    Notification add(Notification N);
    Notification edit(Notification N);
    List<Notification> selectAll();

    Notification selectById(Integer idNotification);


    void deleteById(Integer idNotification);

    void delete (Notification N);

    List <Notification> addAll (List<Notification> list);
    void deleteAll(List<Notification> list);
}
