package com.example.apiserver;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class KafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void create(){
        kafkaTemplate.send("topic", "Hello World");
    }
}
