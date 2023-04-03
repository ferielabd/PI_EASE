package com.example.pi_ease.Services.Classes;

import com.example.pi_ease.DAO.Entities.User;
import com.example.pi_ease.DAO.Repositories.UserRepository;
import com.example.pi_ease.Services.Interface.IUserServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServices implements IUserServices {
    private UserRepository userRepository;

    @Override
    public User add(User U) {

        return userRepository.save(U);
    }

    @Override
    public User edit(User U) {
        return userRepository.save(U);
    }

    @Override
    public List<User> selectAll() {
        return userRepository.findAll();
    }

    @Override
    public User selectById(long idUser) {
        return userRepository.findById(idUser).get();
    }

    @Override
    public void deleteById(long idUser) {
        userRepository.deleteById(idUser);
    }

    @Override
    public void delete(User U) {
        userRepository.delete(U);
    }

    @Override
    public List<User> addAll(List<User> list) {
        return userRepository.saveAll(list);
    }

    @Override
    public void deleteAll(List<User> list) {
        userRepository.deleteAll(list);
    }

    @Override
    public void active(long idUser) {
        User user = userRepository.findById(idUser).get();
        user.setActive(true);
        userRepository.save(user);
    }
}
