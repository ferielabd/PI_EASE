package com.example.pi_ease.Services.Classes;

import com.example.pi_ease.DAO.Entities.Room;
import com.example.pi_ease.DAO.Repositories.RoomRepository;
import com.example.pi_ease.Services.Interface.IRoomServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoomServices implements IRoomServices {
    private RoomRepository roomRepository ;

    @Override
    public Room add(Room R) {
        return roomRepository.save(R) ;
    }

    @Override
    public Room edit(Room R) {

        return roomRepository.save(R) ;
    }
    @Override
    public List<Room> selectAll() {
        return roomRepository.findAll() ;
    }

    @Override
    public Room selectById(int idRoom) {
        return roomRepository.findById(idRoom).get() ;
    }

    @Override
    public void deleteByID(int idRoom) {
        roomRepository.deleteById(idRoom);

    }

    @Override
    public void delete(Room R) {
        roomRepository.delete(R);


    }
    @Override
    public List<Room> addAll(List<Room> list) {
        return list ;
    }

    @Override
    public void deleteAll() {
        roomRepository.deleteAll();

    }




}


