package com.citi.microservice.web.rest;

import com.citi.microservice.service.JhipsterServerApplicationKafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/jhipster-server-application-kafka")
public class JhipsterServerApplicationKafkaResource {

    private final Logger log = LoggerFactory.getLogger(JhipsterServerApplicationKafkaResource.class);

    private JhipsterServerApplicationKafkaProducer kafkaProducer;

    public JhipsterServerApplicationKafkaResource(JhipsterServerApplicationKafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestParam("message") String message) {
        log.debug("REST request to send to Kafka topic the message : {}", message);
        this.kafkaProducer.sendMessage(message);
    }
}
