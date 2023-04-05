package com.example.pi_ease.Services.Classes;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OpenAIService {

    private final OkHttpClient httpClient;
    private final ObjectMapper objectMapper;
    private OpenAIConfig openAIConfig;

    @Autowired
    public void setOpenAIConfig(OpenAIConfig openAIConfig) {
        this.openAIConfig = openAIConfig;
    }

    public OpenAIService() {
        this.httpClient = new OkHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public String getCompletion(String prompt) throws IOException {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"prompt\": \"" + prompt + "\", \"model\": \"text-davinci-002\"}");
        Request request = new Request.Builder()
                .url(openAIConfig.getApiUrl() + "/completions")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + openAIConfig.getApiKey())
                .build();
        Response response = httpClient.newCall(request).execute();

        String responseBody = response.body().string();
        System.out.println(responseBody);
        if(responseBody != null && !responseBody.isEmpty()) {
            return objectMapper.readTree(responseBody).get("choices").get(0).get("text").asText();
        } else {
            throw new IOException("Response body is null or empty.");
        }
    }}
