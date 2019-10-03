package com.citi.microservice.web.rest;

import com.citi.microservice.JhipsterServerApplicationApp;
import com.citi.microservice.config.TestSecurityConfiguration;
import com.citi.microservice.service.JhipsterServerApplicationKafkaProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EmbeddedKafka
@SpringBootTest(classes = {JhipsterServerApplicationApp.class, TestSecurityConfiguration.class})
public class JhipsterServerApplicationKafkaResourceIT {

    @Autowired
    private JhipsterServerApplicationKafkaProducer kafkaProducer;

    private MockMvc restMockMvc;

    @BeforeEach
    public void setup() {
        JhipsterServerApplicationKafkaResource kafkaResource = new JhipsterServerApplicationKafkaResource(kafkaProducer);

        this.restMockMvc = MockMvcBuilders.standaloneSetup(kafkaResource)
            .build();
    }

    @Test
    public void sendMessageToKafkaTopic() throws Exception {
        restMockMvc.perform(post("/api/jhipster-server-application-kafka/publish?message=yolo"))
            .andExpect(status().isOk());
    }
}
