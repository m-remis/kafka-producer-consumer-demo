package com.michal.examples.kafka.producer.service;

import com.michal.examples.kafka.common.models.MessageDto;
import com.michal.examples.kafka.producer.client.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Service
public class MessageProducerService {

    private final KafkaProducer kafkaProducer;

    private final String messageTopic;

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageProducerService.class);

    public MessageProducerService(KafkaProducer kafkaProducer,
                                  @Value("${application.messaging.topic}") String topic) {
        this.kafkaProducer = kafkaProducer;
        this.messageTopic = topic;
    }

    @Scheduled(fixedDelay = 5, timeUnit = TimeUnit.SECONDS)
    public void sendMessage() {
        LOGGER.info("Sending message...");
        kafkaProducer.send(messageTopic, buildMessage());
    }

    private MessageDto buildMessage() {
        return new MessageDto(String.format("[%s] :this is message from producer", LocalDateTime.now(Clock.systemUTC())));
    }

}
