package com.example.pi_ease.Services.Classes;

import com.example.pi_ease.DAO.Entities.Badword;
import com.example.pi_ease.DAO.Repositories.BadwordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

    @Service
    @AllArgsConstructor
    public class BadwordServices {
        private BadwordRepository badWordRepository;

        public List<Badword> getBadWords() {
            return badWordRepository.findAll();
        }

        public void addBadWord(Badword badWord) {
            badWordRepository.save(badWord);
        }

        public void removeBadWord(Long id) {
            badWordRepository.deleteById(id);
        }

}
