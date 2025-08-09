package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestClientConfiguration {

     // First Method: default
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
