package com.example.pi_ease.Services.Classes;

import com.example.pi_ease.DAO.Entities.Notification;
import com.example.pi_ease.DAO.Repositories.NotificationRepository;
import com.example.pi_ease.Services.Interface.INotificationServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NotificationServices implements INotificationServices {
    private NotificationRepository notificationRepositories;
    @Override
    public Notification add(Notification N) {

        return notificationRepositories.save(N);
    }

    @Override
    public Notification edit(Notification N) {

        return notificationRepositories.save(N);
    }

    @Override
    public List<Notification> selectAll() {
        return notificationRepositories.findAll();
    }

    @Override
    public Notification selectById(Integer idNotification) {
        return notificationRepositories.findById(idNotification).get();
    }

    @Override
    public void deleteById(Integer idNotification) {
        notificationRepositories.deleteById(idNotification);

    }

    @Override
    public void delete(Notification N) {
        notificationRepositories.delete(N);

    }

    @Override
    public List<Notification> addAll(List<Notification> list) {
        return notificationRepositories.saveAll(list);
    }

    @Override
    public void deleteAll(List<Notification> list) {
        notificationRepositories.deleteAll(list);

    }

}
