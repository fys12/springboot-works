package com.example.springbootworks.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RocketMQConfig {

    @Bean
    public MappingJackson2MessageConverter rocketMQMessageConverter(ObjectMapper objectMapper) {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setObjectMapper(objectMapper);
        return converter;
    }
}