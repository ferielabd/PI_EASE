package com.example.pi_ease.Services.Classes;

import com.example.pi_ease.DAO.Entities.Reaction;
import com.example.pi_ease.DAO.Entities.Room;
import com.example.pi_ease.DAO.Repositories.ReactionRepository;
import com.example.pi_ease.DAO.Repositories.RoomRepository;
import com.example.pi_ease.Services.Interface.IReactionServices;
import com.example.pi_ease.Services.Interface.IRoomServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReactionServices implements IReactionServices {
    private ReactionRepository  reactionRepository ;

    @Override
    public Reaction add(Reaction Re) {
        return reactionRepository.save(Re) ;
    }

    @Override
    public Reaction edit(Reaction Re) {

        return reactionRepository.save(Re) ;
    }
    @Override
    public List<Reaction> selectAll() {
        return reactionRepository.findAll() ;
    }

    @Override
    public Reaction selectById(int idR) {
        return reactionRepository.findById(idR).get() ;
    }

    @Override
    public void deleteByID(int idR) {
        reactionRepository.deleteById(idR);

    }

    @Override
    public void delete(Reaction Re) {
        reactionRepository.delete(Re);


    }
    @Override
    public List<Reaction> addAll(List<Reaction> list) {
        return list ;
    }

    @Override
    public void deleteAll() {
        reactionRepository.deleteAll();

    }



}
