package com.example.pi_ease.RestController;

import com.example.pi_ease.Services.Classes.OpenAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class OpenAIController {
    @Autowired
    private final OpenAIService openAIService;

    public OpenAIController(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }


    @GetMapping("/openai/completion")
    public String getCompletion(@RequestParam("search") String prompt ) throws IOException {
        return openAIService.getCompletion(prompt);
    }
}