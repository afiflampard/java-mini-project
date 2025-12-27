package com.gdsc.boilerplate.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    
    @KafkaListener(topics = "user-created", groupId = "auth-service")
    public void listenUserCreated(Object message) {
        System.out.println("Received user created message: " + message);
    }
}
