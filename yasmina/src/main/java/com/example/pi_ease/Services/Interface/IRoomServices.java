package com.example.pi_ease.Services.Interface;

import com.example.pi_ease.DAO.Entities.Room;

import java.util.List;

public interface IRoomServices {
    Room add(Room R);
    Room edit(Room R);
    List<Room> selectAll();
    Room selectById(int idRoom);
    void deleteByID (int idRoom);
    void delete (Room R);
    List<Room>addAll(List<Room> list);
    void deleteAll ();
}


