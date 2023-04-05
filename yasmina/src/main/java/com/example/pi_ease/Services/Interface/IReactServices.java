package com.example.pi_ease.Services.Interface;

import com.example.pi_ease.DAO.Entities.React;


import java.util.List;

public interface IReactServices {
    React add(React Re);
    React edit(React Re);

    React selectById(int idReaction);
    void deleteByID (int idReaction);
    void delete(React Re);
    List<React>addAll(List<React> list);
    void deleteAll ();
    void LikeDislikePost(int reaction , Long PostId, Long id);
    void addReactToPost(int reaction , Long PostId, Long id);
    void DislikePost( int idReaction ,  Long id);
    void addReactToComment(int reaction, Long idComm, Long id);
    void DislikeComment(int idReaction, Long id);
}
