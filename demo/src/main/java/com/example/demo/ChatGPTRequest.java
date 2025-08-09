package com.example.demo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatGPTRequest {
    private String model;
    private List<Message> messages;
    private double temperature; // Optional: Controls randomness

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Message {
        private String role; // e.g., "user", "system", "assistant"
        private String content;
    }
}

