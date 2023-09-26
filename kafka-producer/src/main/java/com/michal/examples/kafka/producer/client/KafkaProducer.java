package com.michal.examples.kafka.producer.client;

import org.springframework.kafka.support.SendResult;

import java.util.concurrent.CompletableFuture;

public interface KafkaProducer {

    CompletableFuture<SendResult<String, Object>> send(String topic, Object object);
}
