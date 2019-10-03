package com.citi.microservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class JhipsterServerApplicationKafkaConsumer {

    private final Logger log = LoggerFactory.getLogger(JhipsterServerApplicationKafkaConsumer.class);
    private static final String TOPIC = "topic_jhipsterserverapplication";

    @KafkaListener(topics = "topic_jhipsterserverapplication", groupId = "group_id")
    public void consume(String message) throws IOException {
        log.info("Consumed message in {} : {}", TOPIC, message);
    }
}
