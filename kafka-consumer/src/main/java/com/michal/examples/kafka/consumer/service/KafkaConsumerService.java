package com.michal.examples.kafka.consumer.service;

import com.michal.examples.kafka.common.models.MessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerService.class);

    @KafkaListener(topics = "${application.messaging.topic}", groupId = "${application.messaging.group}")
    public void listen(MessageDto message) {
        LOGGER.info("Received message...");
        LOGGER.info("Message: {}", message.message());
    }
}
