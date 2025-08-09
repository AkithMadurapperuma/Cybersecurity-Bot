package com.example.demo;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ChatGPTService {

    @Value("${openai.api.key}")
    private String openaiApiKey;

    @Value("${openai.api.url}")
    private String openaiApiUrl;

    private final RestTemplate restTemplate;

    public ChatGPTService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getChatGPTResponse(String prompt) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(openaiApiKey);

        // Using gpt-3.5-turbo as a common model
        ChatGPTRequest.Message userMessage = new ChatGPTRequest.Message("user", prompt);
        List<ChatGPTRequest.Message> messages = Collections.singletonList(userMessage);

        ChatGPTRequest request = new ChatGPTRequest("gpt-3.5-turbo", messages, 0.7); // Temperature can be adjusted

        HttpEntity<ChatGPTRequest> entity = new HttpEntity<>(request, headers);

        ChatGPTResponse response = restTemplate.postForObject(openaiApiUrl, entity, ChatGPTResponse.class);

        if (response != null && response.getChoices() != null && !response.getChoices().isEmpty()) {
            return response.getChoices().get(0).getMessage().getContent();
        }
        return "No response from ChatGPT.";
    }
}
