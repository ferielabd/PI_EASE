package com.example.pi_ease.RestControllers;

import com.example.pi_ease.DAO.Entities.Badword;
import com.example.pi_ease.Services.Classes.BadwordServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class BadWordRestController {
    @RestController
    @RequestMapping("/api/badwords")
    public class BadWordController {
        @Autowired
        private BadwordServices badWordService;

        @GetMapping
        public List<Badword> getBadWords() {
            return badWordService.getBadWords();
        }

        @PostMapping
        public void addBadWord(@RequestBody Badword badWord) {
            badWordService.addBadWord(badWord);
        }

        @DeleteMapping("/{id}")
        public void removeBadWord(@PathVariable Long id) {
            badWordService.removeBadWord(id);
        }
    }
}
